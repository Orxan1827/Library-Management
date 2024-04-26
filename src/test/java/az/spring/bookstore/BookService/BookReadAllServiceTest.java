package az.spring.bookstore.BookService;

import az.spring.bookstore.dto.request.book.BookReadRequest;
import az.spring.bookstore.dto.response.book.BookReadResponse;
import az.spring.bookstore.entity.Book;
import az.spring.bookstore.mapper.BookMapper;
import az.spring.bookstore.repository.BookRepository;
import az.spring.bookstore.service.bookService.BookReadAllService;
import az.spring.bookstore.util.BookUtil;
import az.spring.bookstore.wrapper.BookWrapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class BookReadAllServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookReadAllService readAllService;

    @Test
    public void testBookReadAllService_whenGetAllBookCalled_shouldReturnBookReadResponseList() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(Book.builder().id(1L).name("test_book").status("A").build());
        bookList.add(Book.builder().id(2L).name("test_book2").status("A").build());

        List<BookReadResponse> expectedResponseList = new ArrayList<>();
        expectedResponseList.add(BookReadResponse.builder().id(1L).name("test_book").status("A").build());
        expectedResponseList.add(BookReadResponse.builder().id(2L).name("test_book2").status("A").build());

        when(bookRepository.findAll()).thenReturn(bookList);
        when(bookMapper.mapBookReadResponse(any(Book.class))).thenReturn(expectedResponseList.get(0), expectedResponseList.get(1));

        List<BookReadResponse> actualResponses = readAllService.getAllBooks();

        assertEquals(expectedResponseList.size(), actualResponses.size());
        assertNotNull(actualResponses);

        verify(bookRepository, times(1)).findAll();
        verify(bookMapper, times(2)).mapBookReadResponse(any(Book.class));
    }

    @Test
    public void testBookReadAllService_whenGetAllBooksFromLibraryCalledWithBookReadRequest_shouldReturnBookReadResponseList() {
        BookReadRequest request = BookReadRequest.builder().fkLibrarianLibraryId(1L).build();
        List<Book> bookList = BookUtil.bookList();
        List<BookReadResponse> expectedResponseList = BookUtil.bookReadResponseList();

        when(bookRepository.findAll()).thenReturn(bookList);
        when(bookMapper.mapBookReadResponse(any(Book.class))).thenAnswer(
                invocation -> {
                    Book book = invocation.getArgument(0);
                    return new BookReadResponse(book.getId(), book.getName(), book.getStatus(), book.getAuthor(), book.getPrice(), book.getFkLibrarianLibraryId(), book.getFkStudentLibraryId(), book.getFkLibrarianUserId(), book.getFkStudentUserId());
                });

        List<BookReadResponse> actualResponses = readAllService.getAllBooksFromLibrary(request);

        assertNotNull(actualResponses);
        assertEquals(expectedResponseList.size(), actualResponses.size());
        assertEquals(request.getFkLibrarianLibraryId(), actualResponses.get(0).getFkLibrarianLibraryId());
        assertEquals(request.getFkLibrarianLibraryId(), actualResponses.get(1).getFkLibrarianLibraryId());


        verify(bookRepository, times(1)).findAll();
        verify(bookMapper, times(2)).mapBookReadResponse(any(Book.class));
    }

    @Test
    public void testBookReadAllService_whenFindByBookStatusACalled_shouldReturnBookReadResponseList() {
        List<BookWrapper> expectedWrappers = BookUtil.expectedWrappers();

        when(bookRepository.findByBookStatusA()).thenReturn(expectedWrappers);

        List<BookWrapper> actualResponses = readAllService.findByBookStatusA();

        assertNotNull(actualResponses);
        assertEquals(expectedWrappers.size(), actualResponses.size());
        assertEquals(expectedWrappers.get(0).getStatus(), actualResponses.get(0).getStatus());
        assertEquals(expectedWrappers.get(1).getStatus(),actualResponses.get(1).getStatus());

        verify(bookRepository, times(1)).findByBookStatusA();
    }

}
