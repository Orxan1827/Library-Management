package az.spring.bookstore.service.bookService;

import az.spring.bookstore.dto.request.BookReadRequest;
import az.spring.bookstore.dto.response.BookReadDetailsResponse;
import az.spring.bookstore.dto.response.BookReadResponse;
import az.spring.bookstore.entity.Book;
import az.spring.bookstore.entity.Library;
import az.spring.bookstore.exception.BookNotFoundException;
import az.spring.bookstore.mapper.BookMapper;
import az.spring.bookstore.repository.BookRepository;
import az.spring.bookstore.repository.LibraryRepository;
import az.spring.bookstore.service.libraryService.LibraryReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookReadService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    public BookReadResponse getBook(Long id) {
        return bookMapper.mapBookReadResponse(findBook(id));
    }

    public BookReadDetailsResponse getBookDetails(BookReadRequest readRequest) {
        return BookReadDetailsResponse.mapBookToResponseDetails(findBook(readRequest.getLibraryId()));
    }

    protected Book findBook(Long id) {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

}
