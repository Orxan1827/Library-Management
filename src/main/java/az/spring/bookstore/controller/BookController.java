package az.spring.bookstore.controller;

import az.spring.bookstore.dto.request.book.BookCreateRequest;
import az.spring.bookstore.dto.request.book.BookDeleteRequest;
import az.spring.bookstore.dto.request.book.BookReadRequest;
import az.spring.bookstore.dto.request.book.BookUpdateRequest;
import az.spring.bookstore.dto.response.book.BookCreateResponse;
import az.spring.bookstore.dto.response.book.BookReadDetailsResponse;
import az.spring.bookstore.dto.response.book.BookReadResponse;
import az.spring.bookstore.dto.response.book.BookUpdatedResponse;
import az.spring.bookstore.service.bookService.*;
import az.spring.bookstore.wrapper.BookWrapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    private final BookDeleteService deleteService;

    private final BookUpdateService updateService;

    @PostMapping("/create")
    public ResponseEntity<BookCreateResponse> createBook(@Valid @RequestBody BookCreateRequest createRequest) {
       return ResponseEntity.status(CREATED).body(createService.createBook(createRequest));
    }

    @PostMapping("/readAll")
    public ResponseEntity<List<BookReadResponse>> getAllBooks() {
        return ResponseEntity.status(OK).body(readAllService.getAllBooks());
    }

    @PostMapping("/readFromLibrary")
    public ResponseEntity<List<BookReadResponse>> getAllBooksFromLibrary(@Valid @RequestBody BookReadRequest readRequest) {
        return ResponseEntity.status(OK).body(readAllService.getAllBooksFromLibrary(readRequest));
    }

    @PostMapping("/readDetails")
    public ResponseEntity<BookReadDetailsResponse> getBookDetails(@Valid @RequestBody BookReadRequest readRequest) {
        return ResponseEntity.status(OK).body(readService.getBookDetails(readRequest));
    }

    @PostMapping("/readAllDetails")
    public ResponseEntity<BookReadDetailsResponse> getAllBookDetails(@Valid @RequestBody BookReadRequest readRequest) {
        return ResponseEntity.status(OK).body(readService.getBookDetails(readRequest));
    }

    @PostMapping("/readAllForStatus")
    public ResponseEntity<List<BookWrapper>> findByBookStatusA() {
        return ResponseEntity.status(OK).body(readAllService.findByBookStatusA());
    }

    @PostMapping("/delete")
    public void deleteBook(@Valid @RequestBody BookDeleteRequest deleteRequest){
        deleteService.deleteBook(deleteRequest);
    }

    @PostMapping("/deActive")
    public void deActiveBook(@Valid @RequestBody BookDeleteRequest deleteRequest){
        deleteService.deActiveBook(deleteRequest);
    }

    @PostMapping("/update")
    public ResponseEntity<BookUpdatedResponse> updateBook(@Valid @RequestBody BookUpdateRequest updateRequest) {
        return ResponseEntity.status(OK).body(updateService.updateBook(updateRequest));
    }

}
