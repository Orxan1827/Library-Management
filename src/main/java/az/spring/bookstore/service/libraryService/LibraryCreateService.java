package az.spring.bookstore.service.libraryService;

import az.spring.bookstore.dto.request.book.BookAddToLibraryRequest;
import az.spring.bookstore.dto.request.library.LibraryCreateRequest;
import az.spring.bookstore.dto.response.library.LibraryCreateResponse;
import az.spring.bookstore.entity.Book;
import az.spring.bookstore.entity.Library;
import az.spring.bookstore.entity.User;
import az.spring.bookstore.exception.BookNotFoundException;
import az.spring.bookstore.exception.GenericException;
import az.spring.bookstore.mapper.LibraryMapper;
import az.spring.bookstore.repository.BookRepository;
import az.spring.bookstore.repository.LibraryRepository;
import az.spring.bookstore.repository.UserRepository;
import az.spring.bookstore.service.userService.UserReadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static az.spring.bookstore.constant.ExceptionConstant.BOOK_CHECK_MESSAGE;
import static org.springframework.http.HttpStatus.FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibraryCreateService {

    private final LibraryRepository libraryRepository;

    private final LibraryReadService libraryReadService;

    private final LibraryMapper libraryMapper;

    private final BookRepository bookRepository;

    private final UserReadService readService;

    private final UserRepository userRepository;

    @Transactional
    public LibraryCreateResponse createLibrary(LibraryCreateRequest createRequest) {
        User user = readService.findUser(createRequest.getFkUserId());
        checkLibraryExisting(createRequest);
        Library savedLibrary = libraryRepository.save(libraryMapper.mapCreateRequestToEntity(createRequest));
        user.setFkLibraryId(savedLibrary.getId());
        userRepository.save(user);
        log.info("Library created {}", savedLibrary);
        return libraryMapper.mapEntityToCreateResponse(savedLibrary);
    }

    @Transactional
    public void addBookToLibrary(BookAddToLibraryRequest addToLibraryRequest) {
        User user = readService.findUser(addToLibraryRequest.getFkUserId());
        user.setFkBookId(addToLibraryRequest.getFkBookId());
        userRepository.save(user);
        Library library = libraryReadService.findLibrary(user.getFkLibraryId());
        library.setFkBookId(addToLibraryRequest.getFkBookId());
        library.setStatus("A");
        checkBookExisting(addToLibraryRequest.getFkBookId(), user);
        library.getFkBookIds().add(addToLibraryRequest.getFkBookId());
        libraryRepository.save(library);
        Book book = bookRepository.findById(addToLibraryRequest.getFkBookId()).orElseThrow(BookNotFoundException::new);
        book.setFkLibraryId(user.getFkLibraryId());
        book.setFkUserId(addToLibraryRequest.getFkUserId());
        book.setStatus("A");
        bookRepository.save(book);
    }

    protected void checkLibraryExisting(LibraryCreateRequest createRequest) {
        Library library = libraryRepository.findByNameOrFkUserIdAndStatusIn(createRequest.getName(), createRequest.getFkUserId(), List.of("C", "A"));
        if (Objects.nonNull(library)) {
            throw GenericException.builder()
                    .httpStatus(FOUND)
                    .errorMessage("Library: " + createRequest.getName() + " is already exists")
                    .errorCode(FOUND.value()).build();
        }
    }

    public void checkBookExisting(Long bookId, User user) {
        Library library = libraryReadService.findLibrary(user.getFkLibraryId());
        if (library.getFkBookIds().contains(bookId)) {
            throw GenericException.builder()
                    .httpStatus(FOUND)
                    .errorCode(FOUND.value())
                    .errorMessage(BOOK_CHECK_MESSAGE)
                    .build();
        }
    }

    private void checkLibrary(Long fkUserId) {
        boolean isAllReadyExists = libraryRepository.existsLibraryByFkUserId(fkUserId);
        if (isAllReadyExists) {
            throw GenericException.builder()
                    .httpStatus(FOUND)
                    .errorMessage("Library: " + fkUserId + " is already exists")
                    .errorCode(FOUND.value()).build();
        }
    }

}
