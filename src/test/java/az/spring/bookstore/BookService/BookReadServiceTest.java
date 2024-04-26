package az.spring.bookstore.BookService;

import az.spring.bookstore.dto.request.book.BookReadRequest;
import az.spring.bookstore.dto.response.book.BookReadResponse;
import az.spring.bookstore.entity.Book;
import az.spring.bookstore.exception.BookNotFoundException;
import az.spring.bookstore.mapper.BookMapper;
import az.spring.bookstore.repository.BookRepository;
import az.spring.bookstore.service.bookService.BookReadService;
import az.spring.bookstore.util.BookUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class BookReadServiceTest {

    @Mock
    private  BookRepository bookRepository;

    @Mock
    private  BookMapper bookMapper;

    @InjectMocks
    private BookReadService readService;

    @Test
    public void testGetBooksDetails_whenBookReadRequest_shouldReturnBookReadResponse() {
        BookReadResponse expectResponses = BookUtil.bookReadResponseList().get(0);
        BookReadRequest request = BookReadRequest.builder().fkLibrarianLibraryId(1L).build();
        Book book = BookUtil.bookWithIdAndStatus();
        when(bookRepository.findBookByFkLibrarianLibraryId(request.getFkLibrarianLibraryId())).thenReturn(Optional.of(book));
        when(bookMapper.mapBookReadResponse(book)).thenReturn(expectResponses);

        BookReadResponse actualResponses = readService.getBookDetails(request);

        assertNotNull(actualResponses);
        assertEquals(expectResponses, actualResponses);
        assertEquals(request.getFkLibrarianLibraryId(), actualResponses.getFkLibrarianLibraryId());

        verify(bookRepository,times(1)).findBookByFkLibrarianLibraryId(request.getFkLibrarianLibraryId());
        verify(bookMapper, times(1)).mapBookReadResponse(book);
    }

    @Test
    public void testGetBooksDetails_whenBookDoesNotExists_shouldThrowsBookNotFoundException() {
        BookReadRequest request = BookReadRequest.builder().fkLibrarianLibraryId(1L).build();
        Book book = BookUtil.bookWithIdAndStatus();
        when(bookRepository.findBookByFkLibrarianLibraryId(request.getFkLibrarianLibraryId())).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> readService.getBookDetails(request));

        verify(bookRepository,times(1)).findBookByFkLibrarianLibraryId(request.getFkLibrarianLibraryId());
        verify(bookMapper, never()).mapBookReadResponse(book);
    }

    @Test
    public void testFindBook_whenBookExists_shouldReturnBook() {
        Long bookId = 1L;
        Book book = BookUtil.bookWithIdAndStatus();
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Book actualResponses = readService.findBook(bookId);

        assertNotNull(actualResponses);
        assertEquals(book, actualResponses);
        assertEquals(book.getId(), actualResponses.getId());

        verify(bookRepository,times(1)).findById(bookId);
    }

    @Test
    public void testFindBook_whenBookDoesNotExists_shouldThrowsBookNotFoundException() {
        Long bookId = 1L;
        Book book = BookUtil.bookWithIdAndStatus();
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> readService.findBook(bookId));

        verify(bookRepository,times(1)).findById(bookId);
    }

}
