package az.spring.bookstore.BookService;

import az.spring.bookstore.dto.request.book.BookCreateRequest;
import az.spring.bookstore.dto.response.book.BookCreateResponse;
import az.spring.bookstore.entity.Book;
import az.spring.bookstore.mapper.BookMapper;
import az.spring.bookstore.repository.BookRepository;
import az.spring.bookstore.service.bookService.BookCreateService;
import az.spring.bookstore.util.BookUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class BookCreateServiceTest {

    @Mock
    private  BookRepository bookRepository;

    @Mock
    private  BookMapper bookMapper;

    @InjectMocks
    private BookCreateService createService;

    @Test
    public void testBookCreateService_whenBookCreateRequest_shouldReturnBookCreateResponse() {
        BookCreateRequest request = BookUtil.bookCreateRequest();
        BookCreateResponse expect = BookUtil.bookCreateResponse();
        Book book = BookUtil.book();
        Book savedBook = BookUtil.savedBook();

        when(bookMapper.mapCreateRequestToEntity(request)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(savedBook);
        when(bookMapper.mapEntityToBookCreateResponse(savedBook)).thenReturn(expect);

        BookCreateResponse actual = createService.createBook(request);

        Assertions.assertEquals(actual, expect);
        assertNotNull(actual);

        verify(bookMapper, times(1)).mapCreateRequestToEntity(request);
        verify(bookRepository, times(1)).save(book);
        verify(bookMapper, times(1)).mapEntityToBookCreateResponse(savedBook);
    }

}
