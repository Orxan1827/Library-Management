package az.spring.bookstore.LibraryService;

import az.spring.bookstore.dto.request.book.BookAddToLibraryRequest;
import az.spring.bookstore.dto.request.book.BookAddToStudentRequest;
import az.spring.bookstore.entity.Book;
import az.spring.bookstore.entity.Library;
import az.spring.bookstore.entity.User;
import az.spring.bookstore.exception.GenericException;
import az.spring.bookstore.exception.LibraryNotFoundException;
import az.spring.bookstore.exception.UnauthorizedAccessException;
import az.spring.bookstore.repository.BookRepository;
import az.spring.bookstore.service.bookService.BookReadService;
import az.spring.bookstore.service.libraryService.LibraryAddService;
import az.spring.bookstore.service.libraryService.LibraryReadService;
import az.spring.bookstore.service.userService.UserReadService;
import az.spring.bookstore.util.LibraryUtil;
import az.spring.bookstore.util.UserUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Optional;

import static az.spring.bookstore.enums.Role.LIBRARIAN;
import static az.spring.bookstore.enums.Role.STUDENT;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class LibraryAddServiceTest {

    @Mock
    private LibraryReadService libraryReadService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserReadService readService;

    @Mock
    private BookReadService bookReadService;

    @InjectMocks
    private LibraryAddService addService;

    @Test
    public void testLibraryAddService_whenBookAddToLibrary() {
        Long fkUserId = 1L;
        Long fkBookId = 1L;
        Long libraryId = 1L;
        User user = UserUtil.user();
        user.setRole(LIBRARIAN);
        user.setFkLibraryId(libraryId);
        BookAddToLibraryRequest addToLibraryRequest = LibraryUtil.BookAddToLibraryRequest();

        when(readService.findUser(fkUserId)).thenReturn(user);
        when(bookRepository.findById(fkBookId)).thenReturn(Optional.of(new Book()));

        addService.addBookToLibrary(addToLibraryRequest);

        verify(bookRepository, times(1)).findById(fkBookId);
        verify(bookRepository, times(1)).save(any());
    }

    @Test
    void testAddBookToLibrary_UserNotLibrarian_shouldThrowUnauthorizedAccessException() {
        Long userId = 1L;
        Long fkBookId = 1L;

        BookAddToLibraryRequest addToLibraryRequest = LibraryUtil.BookAddToLibraryRequest();
        User user = UserUtil.user();
        user.setRole(STUDENT);
        when(readService.findUser(userId)).thenReturn(user);

        assertThrows(UnauthorizedAccessException.class, () -> addService.addBookToLibrary(addToLibraryRequest));

        verify(readService, times(1)).findUser(userId);
        verify(bookRepository, never()).findById(fkBookId);
        verify(bookRepository, never()).save(any());
    }

    @Test
    void testAddBookToLibrary_LibraryNotFound_shouldThrowGenericException() {
        Long fkBookId = 1L;
        Long userId = 1L;
        BookAddToLibraryRequest addToLibraryRequest = LibraryUtil.BookAddToLibraryRequest();
        User user = UserUtil.user();
        user.setRole(LIBRARIAN);
        user.setFkLibraryId(null);

        when(readService.findUser(userId)).thenReturn(user);

        assertThrows(GenericException.class, () -> addService.addBookToLibrary(addToLibraryRequest));

        verify(readService, times(1)).findUser(userId);
        verify(bookRepository, never()).findById(fkBookId);
        verify(bookRepository, never()).save(any());
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testAddBookToStudentLibrary_whenUserNotStudent_shouldThrowsUnauthorizedAccessException() {
        Long fkBookId = 1L;
        Long fkUserId = 1L;
        Long fkLibraryId = 1L;
        User user = UserUtil.user();
        user.setRole(LIBRARIAN);
        BookAddToStudentRequest addToStudentRequest = BookAddToStudentRequest.builder()
                .fkBookId(fkBookId)
                .fkUserId(fkUserId)
                .fkLibraryId(fkLibraryId)
                .build();
        when(readService.findUser(fkUserId)).thenReturn(user);

        assertThrows(UnauthorizedAccessException.class, () -> addService.addBookToStudentLibrary(addToStudentRequest));

        verify(readService, times(1)).findUser(fkUserId);
        verify(bookRepository, never()).findById(fkBookId);
        verify(bookRepository, never()).save(any());
    }

    @Test
    void testAddBookToStudentLibrary_StudentLibraryNotFound_shouldThrowsLibraryNotFoundException() {
        Long fkBookId = 1L;
        Long fkUserId = 1L;
        Long fkLibraryId = 2L;
        BookAddToStudentRequest addToStudentRequest = new BookAddToStudentRequest(fkUserId, fkBookId, fkLibraryId);
        User user = UserUtil.userWithId();
        user.setRole(STUDENT);
        user.setFkLibraryId(1L);

        when(readService.findUser(fkUserId)).thenReturn(user);
        when(libraryReadService.findLibrary(user.getFkLibraryId())).thenThrow(LibraryNotFoundException.class);

        assertThrows(LibraryNotFoundException.class, () -> addService.addBookToStudentLibrary(addToStudentRequest));
        
        verify(readService, times(1)).findUser(fkUserId);
        verify(bookRepository, never()).findById(fkBookId);
        verify(bookRepository, never()).save(any());
    }

    @Test
    void testAddBookToStudentLibrary_BookNotAvailable_shouldThrowsGenericException() {
        Long fkBookId = 1L;
        Long fkUserId = 1L;
        Long fkLibraryId = 2L;
        Long studentLibraryId = 1L;
        BookAddToStudentRequest addToStudentRequest = new BookAddToStudentRequest(fkUserId, fkBookId, studentLibraryId);
        User user = UserUtil.userWithId();
        user.setRole(STUDENT);
        user.setFkLibraryId(studentLibraryId);

        when(readService.findUser(fkUserId)).thenReturn(user);
        when(libraryReadService.findLibrary(studentLibraryId)).thenReturn(new Library());
        when(libraryReadService.findLibrary(fkLibraryId)).thenReturn(new Library());
        when(bookReadService.findBook(fkBookId)).thenReturn(new Book());
        when(bookRepository.existsBookByFkLibrarianLibraryIdAndId(fkLibraryId, fkBookId)).thenReturn(false);

        assertThrows(GenericException.class, () -> addService.addBookToStudentLibrary(addToStudentRequest));
    }

    @Test
    void testAddBookToStudentLibrary_whenBookAddToStudentRequest_success() {
        Long fkBookId = 1L;
        Long fkUserId = 1L;
        Long fkLibraryId = 2L;
        Long studentLibraryId = 1L;
        BookAddToStudentRequest addToStudentRequest = new BookAddToStudentRequest(fkUserId, fkBookId, fkLibraryId);
        User user = UserUtil.userWithId();
        user.setRole(STUDENT);
        user.setFkLibraryId(studentLibraryId);
        Library studentLibrary = LibraryUtil.library();
        Library librarianLibrary = LibraryUtil.librarianLibrary();

        when(readService.findUser(fkUserId)).thenReturn(user);
        when(libraryReadService.findLibrary(studentLibraryId)).thenReturn(studentLibrary);
        when(libraryReadService.findLibrary(fkLibraryId)).thenReturn(librarianLibrary);
        when(bookReadService.findBook(fkBookId)).thenReturn(new Book());
        when(bookRepository.existsBookByFkLibrarianLibraryIdAndId(fkLibraryId, fkBookId)).thenReturn(true);
        when(bookRepository.save(any())).thenReturn(any());

        addService.addBookToStudentLibrary(addToStudentRequest);

        verify(readService, times(1)).findUser(fkUserId);
        verify(libraryReadService, times(1)).findLibrary(studentLibraryId);
        verify(libraryReadService, times(1)).findLibrary(fkLibraryId);
        verify(bookReadService, times(1)).findBook(fkBookId);
        verify(bookRepository, times(1)).existsBookByFkLibrarianLibraryIdAndId(fkLibraryId, fkBookId);
        verify(bookRepository, times(1)).save(any());
    }

}
