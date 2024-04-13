package az.spring.bookstore.service.libraryService;

import az.spring.bookstore.dto.request.BookAddToLibraryRequest;
import az.spring.bookstore.dto.request.LibraryCreateRequest;
import az.spring.bookstore.entity.Library;
import az.spring.bookstore.entity.User;
import az.spring.bookstore.exception.GenericException;
import az.spring.bookstore.repository.BookRepository;
import az.spring.bookstore.repository.LibraryRepository;
import az.spring.bookstore.repository.UserRepository;
import az.spring.bookstore.service.userService.UserReadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibraryCreateService {

    private final LibraryRepository libraryRepository;

    private final LibraryReadService libraryReadService;

    private final BookRepository bookRepository;

    private final UserReadService readService;
    private final UserRepository userRepository;

    public void createLibrary(LibraryCreateRequest createRequest) {

        checkLibrary(createRequest.getUserId());
        User user = new User();
        user.setId(createRequest.getUserId());
        Library library = new Library();
        library.setUser(user);
        libraryRepository.save(library);

    }

    public void createLibrary2(LibraryCreateRequest createRequest) {

        checkLibrary(createRequest.getUserId());
        User user = readService.findUser(createRequest.getUserId());
        Library library = new Library();
        library.setFkUserId(user.getId());
        libraryRepository.save(library);
        user.setFkLibraryId(library.getId());
        userRepository.save(user);

    }

    @Transactional
    public void addBookToLibrary(BookAddToLibraryRequest addToLibraryRequest) {

//        User user = readService.findUser(addToLibraryRequest.getUserId());
//        user.get
//        Library library = libraryReadService.findLibrary(user.getFkLibraryId());
//        library.setFkBookId(addToLibraryRequest.getBookId());
//        userRepository.save(user);
//        libraryRepository.save(library);
//        Book book = bookRepository.findById(addToLibraryRequest.getBookId())
//                .orElseThrow(BookNotFoundException::new);
//        book.setFkLibraryId(user.getFkLibraryId());
//        book.setFkUserId(addToLibraryRequest.getUserId());
//        bookRepository.save(book);

    }

    protected void checkLibrary(Long id) {

        boolean isAllReadyExist = libraryRepository.existsLibraryByFkUserId(id);
        if (isAllReadyExist) {
            throw GenericException
                    .builder()
                    .httpStatus(HttpStatus.FOUND)
                    .errorMessage("Library: " + id + " is already exists")
                    .errorCode("409")
                    .build();
        }

    }
}
