package az.spring.bookstore.controller;

import az.spring.bookstore.dto.request.BookCreateRequest;
import az.spring.bookstore.dto.request.BookReadRequest;
import az.spring.bookstore.dto.response.BookReadDetailsResponse;
import az.spring.bookstore.dto.response.BookReadResponse;
import az.spring.bookstore.service.bookService.BookCreateService;
import az.spring.bookstore.service.bookService.BookReadAllService;
import az.spring.bookstore.service.bookService.BookReadService;
import az.spring.bookstore.wrapper.BookWrapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookCreateService createService;

    private final BookReadService readService;

    private final BookReadAllService readAllService;

    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public void createBook(@Valid @RequestBody BookCreateRequest createRequest) {
        createService.createBook(createRequest);
    }

    @PostMapping("/readFromLibrary")
    public ResponseEntity<List<BookReadResponse>> getAllBooksFromLibrary(@Valid @RequestBody BookReadRequest readRequest) {
        return ResponseEntity.ok(readAllService.getAllBooksFromLibrary(readRequest));
    }

    @PostMapping("/readALl")
    public ResponseEntity<List<BookReadResponse>> getAllBooks() {
        return ResponseEntity.status(OK).body(readAllService.getAllBooks());
    }

    @PostMapping("/read2")
    public ResponseEntity<List<BookReadResponse>> getAllBooksForLibrary(@Valid @RequestBody BookReadRequest readRequest) {
        return ResponseEntity.ok(readAllService.getAllBooksForLibrary(readRequest));
    }

    @PostMapping("/readDetails")
    public ResponseEntity<BookReadDetailsResponse> getBookDetails(@Valid @RequestBody BookReadRequest readRequest) {
        return ResponseEntity.ok(readService.getBookDetails(readRequest));
    }

    @PostMapping("/readAllForStatus")
    public ResponseEntity<List<BookWrapper>> findByBookStatusA() {
        return ResponseEntity.status(OK).body(readAllService.findByBookStatusA());
    }

}
