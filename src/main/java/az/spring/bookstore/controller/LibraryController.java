package az.spring.bookstore.controller;

import az.spring.bookstore.dto.request.book.BookAddToLibraryRequest;
import az.spring.bookstore.dto.request.library.LibraryCreateRequest;
import az.spring.bookstore.dto.request.library.LibraryDeleteRequest;
import az.spring.bookstore.dto.request.library.LibraryReadBookIdRequest;
import az.spring.bookstore.dto.request.library.LibraryUpdateRequest;
import az.spring.bookstore.dto.response.library.LibraryCreateResponse;
import az.spring.bookstore.service.libraryService.LibraryCreateService;
import az.spring.bookstore.service.libraryService.LibraryDeleteService;
import az.spring.bookstore.service.libraryService.LibraryReadAllService;
import az.spring.bookstore.service.libraryService.LibraryUpdateService;
import az.spring.bookstore.wrapper.LibraryWrapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/library")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryCreateService createService;

    private final LibraryReadAllService readAllService;

    private final LibraryUpdateService updateService;

    private final LibraryDeleteService deleteService;

    @PostMapping("/create")
    public ResponseEntity<LibraryCreateResponse> createLibrary(@Valid @RequestBody LibraryCreateRequest createRequest) {
        return ResponseEntity.status(CREATED).body(createService.createLibrary(createRequest));
    }

    @PostMapping("/add")
    public void addBookToLibrary(@RequestBody BookAddToLibraryRequest addToLibraryRequest) {
        createService.addBookToLibrary(addToLibraryRequest);
    }

    @PostMapping("/readAllForStatus")
    public ResponseEntity<List<LibraryWrapper>> findByLibraryStatusA() {
        return ResponseEntity.status(OK).body(readAllService.findByLibraryStatusA());
    }

    @PostMapping("/readAllBookId")
    public ResponseEntity<List<LibraryWrapper>> findAllByFkBookId(@Valid @RequestBody LibraryReadBookIdRequest readBookIdRequest) {
        return ResponseEntity.status(OK).body(readAllService.findAllByFkBookId(readBookIdRequest));
    }

    @PostMapping("/update")
    public void updateLibrary(@Valid @RequestBody LibraryUpdateRequest updateRequest) {
        updateService.updateLibrary(updateRequest);
    }

    @PostMapping("/delete")
    public void deleteLibrary(@Valid @RequestBody LibraryDeleteRequest deleteRequest) {
        deleteService.deleteLibrary(deleteRequest);
    }
}