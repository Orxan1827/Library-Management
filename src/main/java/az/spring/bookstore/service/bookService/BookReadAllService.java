package az.spring.bookstore.service.bookService;

import az.spring.bookstore.dto.request.BookReadRequest;
import az.spring.bookstore.dto.response.BookReadResponse;
import az.spring.bookstore.entity.Library;
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

    private final LibraryReadService libraryReadService;

    private final BookMapper bookMapper;

    public List<BookReadResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::mapBookReadResponse)
                .toList();
    }

    public List<BookReadResponse> getAllBooksFromLibrary(BookReadRequest readRequest) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getLibrary().getId().equals(readRequest.getLibraryId()))
                .map(bookMapper::mapBookReadResponse)
                .collect(Collectors.toList());

    }

    public List<BookReadResponse> getAllBooksForLibrary(BookReadRequest readRequest) {
        Library library = libraryReadService.findLibrary(readRequest.getLibraryId());
        return library.getBooks().stream()
                .map(bookMapper::mapBookReadResponse)
                .toList();
    }

    public List<BookWrapper> findByBookStatusA() {
        return bookRepository.findByBookStatusA();
    }
}
