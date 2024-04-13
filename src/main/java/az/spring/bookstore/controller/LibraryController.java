package az.spring.bookstore.controller;

import az.spring.bookstore.dto.request.BookAddToLibraryRequest;
import az.spring.bookstore.dto.request.LibraryCreateRequest;
import az.spring.bookstore.service.libraryService.LibraryCreateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/library")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryCreateService createService;

    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public void createLibrary(@Valid @RequestBody LibraryCreateRequest createRequest) {
        createService.createLibrary2(createRequest);
    }

    @PostMapping("/add")
    public void addBookToLibrary(@RequestBody BookAddToLibraryRequest addToLibraryRequest){
        createService.addBookToLibrary(addToLibraryRequest);
    }

}