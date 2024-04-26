package az.spring.bookstore.service.bookService;

import az.spring.bookstore.dto.request.book.BookReadRequest;
import az.spring.bookstore.dto.response.book.BookReadDetailsResponse;
import az.spring.bookstore.dto.response.book.BookReadResponse;
import az.spring.bookstore.mapper.BookMapper;
import az.spring.bookstore.repository.BookRepository;
import az.spring.bookstore.service.libraryService.LibraryReadService;
import az.spring.bookstore.wrapper.BookWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookReadAllService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    public List<BookReadResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::mapBookReadResponse)
                .toList();
    }

    public List<BookReadResponse> getAllBooksFromLibrary(BookReadRequest readRequest) {
        return bookRepository.findAll().stream()
                .filter(book -> readRequest.getFkLibrarianLibraryId().equals(book.getFkLibrarianLibraryId()))
                .map(bookMapper::mapBookReadResponse)
                .collect(Collectors.toList());
    }

    public List<BookWrapper> findByBookStatusA() {
        return bookRepository.findByBookStatusA();
    }

}
