package az.spring.bookstore.BookService;

import az.spring.bookstore.dto.request.book.BookUpdateRequest;
import az.spring.bookstore.dto.response.book.BookUpdatedResponse;
import az.spring.bookstore.entity.Book;
import az.spring.bookstore.entity.User;
import az.spring.bookstore.exception.BookNotFoundException;
import az.spring.bookstore.exception.UnauthorizedAccessException;
import az.spring.bookstore.exception.UserNotFoundException;
import az.spring.bookstore.mapper.BookMapper;
import az.spring.bookstore.repository.BookRepository;
import az.spring.bookstore.service.bookService.BookReadService;
import az.spring.bookstore.service.bookService.BookUpdateService;
import az.spring.bookstore.service.userService.UserReadService;
import az.spring.bookstore.util.BookUtil;
import az.spring.bookstore.util.UserUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.math.BigDecimal;

import static az.spring.bookstore.enums.Role.LIBRARIAN;
import static az.spring.bookstore.enums.Role.STUDENT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class BookUpdateServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookReadService readService;

    @Mock
    private UserReadService userReadService;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookUpdateService updateService;

    @Test
    public void testBookUpdateService_whenUpdateRequest_shouldReturnBookUpdatedResponse() {
        User user = UserUtil.userWithId();
        user.setRole(LIBRARIAN);
        Book book = BookUtil.book();
        Book updatedBook = BookUtil.updatedBook();
        BookUpdateRequest request = BookUtil.updateRequest();
        BookUpdatedResponse expectResponse = BookUtil.updatedResponse();

        when(userReadService.findUser(request.getUserId())).thenReturn(user);
        when(readService.findBook(request.getId())).thenReturn(book);
        when(bookMapper.updateBookFromUpdateRequest(request, book)).thenReturn(updatedBook);
        when(bookRepository.save(updatedBook)).thenReturn(updatedBook);
        when(bookMapper.mapBookToUpdatedResponse(updatedBook)).thenReturn(expectResponse);

        BookUpdatedResponse actualResponse = updateService.updateBook(request);

        assertNotNull(actualResponse);
        assertEquals(actualResponse, expectResponse);
        assertEquals(actualResponse.getId(), expectResponse.getId());

        verify(userReadService, times(1)).findUser(request.getUserId());
        verify(readService, times(1)).findBook(request.getId());
        verify(bookMapper, times(1)).updateBookFromUpdateRequest(request, book);
        verify(bookRepository, times(1)).save(updatedBook);
        verify(bookMapper, times(1)).mapBookToUpdatedResponse(updatedBook);
    }

    @Test
    public void testBookUpdateService_whenUserNotLibrarian_shouldThrowsUnauthorizedAccessException() {
        User user = UserUtil.userWithId();
        user.setRole(STUDENT);
        Book book = BookUtil.book();
        Book updatedBook = BookUtil.updatedBook();
        BookUpdateRequest request = BookUpdateRequest.builder().id(1L).name("test_book").author("test_author").price(BigDecimal.TEN).userId(1L).build();

        when(userReadService.findUser(request.getUserId())).thenReturn(user);

        assertThrows(UnauthorizedAccessException.class, () -> updateService.updateBook(request));

        verify(userReadService, times(1)).findUser(request.getUserId());
        verify(readService, never()).findBook(request.getId());
        verify(bookMapper, never()).updateBookFromUpdateRequest(request, book);
        verify(bookRepository, never()).save(updatedBook);
        verify(bookMapper, never()).mapBookToUpdatedResponse(updatedBook);
    }

    @Test
    public void testBookUpdateService_whenUserDoesNotExists_shouldThrowsUserNotFoundException() {
        User user = UserUtil.userWithId();
        user.setRole(LIBRARIAN);
        Book book = BookUtil.book();
        Book updatedBook = BookUtil.updatedBook();
        BookUpdateRequest request = BookUpdateRequest.builder().id(1L).name("test_book").author("test_author").price(BigDecimal.TEN).userId(1L).build();

        when(userReadService.findUser(request.getUserId())).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> updateService.updateBook(request));

        verify(userReadService, times(1)).findUser(request.getUserId());
        verify(readService, never()).findBook(request.getId());
        verify(bookMapper, never()).updateBookFromUpdateRequest(request, book);
        verify(bookRepository, never()).save(updatedBook);
        verify(bookMapper, never()).mapBookToUpdatedResponse(updatedBook);
    }

    @Test
    public void testBookUpdateService_whenBookDoesNotExists_shouldThrowsUserNotFoundException() {
        User user = UserUtil.userWithId();
        user.setRole(LIBRARIAN);
        Book book = BookUtil.book();
        Book updatedBook = BookUtil.updatedBook();
        BookUpdateRequest request = BookUpdateRequest.builder().id(1L).name("test_book").author("test_author").price(BigDecimal.TEN).userId(1L).build();

        when(userReadService.findUser(request.getUserId())).thenReturn(user);
        when(readService.findBook(request.getId())).thenThrow(BookNotFoundException.class);

        assertThrows(BookNotFoundException.class, () -> updateService.updateBook(request));

        verify(userReadService, times(1)).findUser(request.getUserId());
        verify(readService, times(1)).findBook(request.getId());
        verify(bookMapper, never()).updateBookFromUpdateRequest(request, book);
        verify(bookRepository, never()).save(updatedBook);
        verify(bookMapper, never()).mapBookToUpdatedResponse(updatedBook);
    }

}
