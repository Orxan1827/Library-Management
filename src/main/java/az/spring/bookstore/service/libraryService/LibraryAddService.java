package az.spring.bookstore.service.libraryService;

import az.spring.bookstore.dto.request.book.BookAddToLibraryRequest;
import az.spring.bookstore.dto.request.book.BookAddToStudentRequest;
import az.spring.bookstore.entity.Book;
import az.spring.bookstore.entity.Library;
import az.spring.bookstore.entity.User;
import az.spring.bookstore.enums.Role;
import az.spring.bookstore.exception.BookNotFoundException;
import az.spring.bookstore.exception.GenericException;
import az.spring.bookstore.exception.UnauthorizedAccessException;
import az.spring.bookstore.repository.BookRepository;
import az.spring.bookstore.service.bookService.BookReadService;
import az.spring.bookstore.service.userService.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static az.spring.bookstore.constant.ExceptionConstant.*;
import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class LibraryAddService {

    private final LibraryReadService libraryReadService;

    private final BookRepository bookRepository;

    private final UserReadService readService;

    private final BookReadService bookReadService;

    @Transactional
    public void addBookToLibrary(BookAddToLibraryRequest addToLibraryRequest) {
        User user = readService.findUser(addToLibraryRequest.getFkUserId());
        if (!user.getRole().equals(Role.LIBRARIAN)) {
            throw new UnauthorizedAccessException();
        }
        Long libraryId = user.getFkLibraryId();
        if (libraryId == null) {
            throw GenericException.builder()
                    .errorCode(NOT_FOUND.value())
                    .httpStatus(NOT_FOUND)
                    .errorMessage(LIBRARY_NOT_FOUND)
                    .build();
        }
        Book book = bookRepository.findById(addToLibraryRequest.getFkBookId())
                .orElseThrow(BookNotFoundException::new);
        book.setFkLibrarianLibraryId(libraryId);
        book.setFkLibrarianUserId(addToLibraryRequest.getFkUserId());
        book.setStatus("A");
        bookRepository.save(book);
    }

    @Transactional
    public void addBookToStudentLibrary(BookAddToStudentRequest addToStudentRequest) {
        User user = readService.findUser(addToStudentRequest.getFkUserId());
        if (!user.getRole().equals(Role.STUDENT)) {
            throw new UnauthorizedAccessException();
        }
        Library studentLibrary = libraryReadService.findLibrary(user.getFkLibraryId());
        Library librarianLibrary = libraryReadService.findLibrary(addToStudentRequest.getFkLibraryId());
        Book bookToAdd = bookReadService.findBook(addToStudentRequest.getFkBookId());
        boolean result = bookRepository.existsBookByFkLibrarianLibraryIdAndId(librarianLibrary.getId(), addToStudentRequest.getFkBookId());
        if (!result) {
            throw GenericException.builder()
                    .httpStatus(NOT_FOUND)
                    .errorCode(NOT_FOUND.value())
                    .errorMessage(BOOK_ORDER_EXCEPTION)
                    .build();
        }
        bookToAdd.setFkStudentLibraryId(studentLibrary.getId());
        bookToAdd.setFkStudentUserId(user.getId());
        bookRepository.save(bookToAdd);
    }

//    public void checkBookExisting(Long bookId, User user) {
//        Library studentLibrary = libraryReadService.findLibrary(user.getFkLibraryId());
//        Book foundedBook = bookReadService.findBook(bookId);
//        if (foundedBook.getFkStudentLibraryId().equals(studentLibrary.getId())) {
//            throw GenericException.builder()
//                    .httpStatus(FOUND)
//                    .errorCode(FOUND.value())
//                    .errorMessage(BOOK_CHECK_MESSAGE)
//                    .build();
//        }
//    }
}
