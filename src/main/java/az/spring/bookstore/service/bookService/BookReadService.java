package az.spring.bookstore.service.bookService;

import az.spring.bookstore.dto.request.book.BookReadRequest;
import az.spring.bookstore.dto.response.book.BookReadDetailsResponse;
import az.spring.bookstore.dto.response.book.BookReadResponse;
import az.spring.bookstore.entity.Book;
import az.spring.bookstore.exception.BookNotFoundException;
import az.spring.bookstore.mapper.BookMapper;
import az.spring.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookReadService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    public BookReadResponse getBookDetails(BookReadRequest readRequest) {
        Book book = bookRepository.findBookByFkLibrarianLibraryId(readRequest.getFkLibrarianLibraryId())
                .orElseThrow(BookNotFoundException::new);
        return bookMapper.mapBookReadResponse(book);
    }

    public Book findBook(Long id) {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

}
