package az.spring.bookstore.BookService;

import az.spring.bookstore.dto.request.book.BookDeleteRequest;
import az.spring.bookstore.entity.Book;
import az.spring.bookstore.entity.User;
import az.spring.bookstore.exception.BookNotFoundException;
import az.spring.bookstore.exception.UnauthorizedAccessException;
import az.spring.bookstore.repository.BookRepository;
import az.spring.bookstore.service.bookService.BookDeleteService;
import az.spring.bookstore.service.bookService.BookReadService;
import az.spring.bookstore.service.userService.UserReadService;
import az.spring.bookstore.util.BookUtil;
import az.spring.bookstore.util.UserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static az.spring.bookstore.enums.Role.LIBRARIAN;
import static az.spring.bookstore.enums.Role.STUDENT;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class BookDeleteServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookReadService readService;

    @Mock
    private UserReadService userReadService;

    @InjectMocks
    private BookDeleteService deleteService;

    @Test
    public void testDeleteBookService_whenDeleteRequest_shouldDeleteBook() {
        Long bookId = 1L;
        User user = UserUtil.userWithId();
        user.setRole(LIBRARIAN);
        Book book = BookUtil.book();
        BookDeleteRequest request = BookUtil.bookDeleteRequest();

        when(userReadService.findUser(request.getUserId())).thenReturn(user);
        when(readService.findBook(bookId)).thenReturn(book);

        deleteService.deleteBook(request);

        verify(userReadService, times(1)).findUser(request.getUserId());
        verify(bookRepository, times(1)).delete(book);

    }

    @Test
    public void testDeleteBookService_whenUserNotLibrarian_shouldThrowsUnauthorizedAccessException() {
        Long bookId = 1L;
        User user = UserUtil.userWithId();
        user.setRole(STUDENT);
        Book book = BookUtil.book();
        BookDeleteRequest request = BookUtil.bookDeleteRequest();

        when(userReadService.findUser(user.getId())).thenReturn(user);

        assertThrows(UnauthorizedAccessException.class, () -> deleteService.deleteBook(request));

        verify(userReadService, times(1)).findUser(user.getId());
        verify(readService, never()).findBook(bookId);
        verify(bookRepository, never()).delete(book);
    }

    @Test
    public void testDeleteBookService_whenBookDoesNotExists_shouldThrowsBookNotFoundException() {
        Long bookId = 1L;
        User user = UserUtil.userWithId();
        user.setRole(LIBRARIAN);
        Book book = BookUtil.book();
        BookDeleteRequest request = BookUtil.bookDeleteRequest();

        when(userReadService.findUser(user.getId())).thenReturn(user);
        when(readService.findBook(bookId)).thenThrow(BookNotFoundException.class);

        assertThrows(BookNotFoundException.class, () -> deleteService.deleteBook(request));

        verify(userReadService, times(1)).findUser(user.getId());
        verify(readService, times(1)).findBook(bookId);
        verify(bookRepository, never()).delete(book);
    }

    @Test
    public void testDeActiveService_whenBookDeleteRequest_shouldDeActiveBook() {
        Book book = BookUtil.book();
        BookDeleteRequest request = BookUtil.bookDeleteRequest();
        
        when(readService.findBook(request.getBookId())).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);

        deleteService.deActiveBook(request);

        verify(readService, times(1)).findBook(request.getBookId());
        verify(bookRepository, times(1)).save(book);
    }
}
