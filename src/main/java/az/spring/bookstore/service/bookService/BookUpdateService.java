package az.spring.bookstore.service.bookService;

import az.spring.bookstore.dto.request.book.BookUpdateRequest;
import az.spring.bookstore.dto.response.book.BookUpdatedResponse;
import az.spring.bookstore.entity.Book;
import az.spring.bookstore.mapper.BookMapper;
import az.spring.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookUpdateService {

    private final BookRepository bookRepository;

    private final BookReadService readService;

    private final BookMapper bookMapper;

    public BookUpdatedResponse updateBook(BookUpdateRequest updateRequest) {
        Book updatedBook = bookMapper.updateBookFromUpdateRequest(updateRequest, readService.findBook(updateRequest.getId()));
        return bookMapper.mapBookToUpdatedResponse(bookRepository.save(updatedBook));
    }

}
