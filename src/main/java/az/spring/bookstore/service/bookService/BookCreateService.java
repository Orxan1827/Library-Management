package az.spring.bookstore.service.bookService;

import az.spring.bookstore.dto.request.BookCreateRequest;
import az.spring.bookstore.entity.Book;
import az.spring.bookstore.entity.Library;
import az.spring.bookstore.entity.User;
import az.spring.bookstore.repository.BookRepository;
import az.spring.bookstore.service.libraryService.LibraryReadService;
import az.spring.bookstore.service.userService.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookCreateService {

    private final BookRepository bookRepository;

    private final BookReadService bookReadService;

    private final LibraryReadService libraryReadService;

    private final UserReadService userReadService;

    @Transactional
    public void createBook2(BookCreateRequest createRequest) {

//        User user = userReadService.findUser(createRequest.getUserId());
//        Library library = libraryReadService.findLibrary(createRequest.getLibraryId());
//        Book book = Book.builder()
//                .name(createRequest.getName())
//                .user(user)
//                .library(library)
//                .build();
//        bookRepository.save(book);

    }

    public void createBook(BookCreateRequest createRequest) {

       Book book = Book.builder()
                .name(createRequest.getName())
                .build();
        bookRepository.save(book);

    }

}
