package az.spring.bookstore.util;

import az.spring.bookstore.dto.request.book.BookCreateRequest;
import az.spring.bookstore.dto.request.book.BookDeleteRequest;
import az.spring.bookstore.dto.request.book.BookUpdateRequest;
import az.spring.bookstore.dto.response.book.BookCreateResponse;
import az.spring.bookstore.dto.response.book.BookReadResponse;
import az.spring.bookstore.dto.response.book.BookUpdatedResponse;
import az.spring.bookstore.entity.Book;
import az.spring.bookstore.wrapper.BookWrapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static az.spring.bookstore.dto.response.book.BookCreateResponse.builder;

public class BookUtil {

    private BookUtil() {

    }

    public static BookCreateRequest bookCreateRequest() {
        return BookCreateRequest.builder().name("test_book").author("test_author").price(BigDecimal.TEN).build();
    }

    public static BookCreateResponse bookCreateResponse() {
        return builder().name("test_book").author("test_author").price(BigDecimal.TEN).build();
    }

    public static BookDeleteRequest bookDeleteRequest() {
        return BookDeleteRequest.builder().bookId(1L).userId(1L).build();
    }

    public static BookUpdateRequest updateRequest() {
        return BookUpdateRequest.builder().id(1L).name("test_book").author("test_author").price(BigDecimal.TEN).userId(1L).build();
    }

    public static BookUpdatedResponse updatedResponse() {
        return BookUpdatedResponse.builder().id(1L).name("test_book").author("test_author").price(BigDecimal.TEN).fkUserId(1L).build();
    }

    public static List<BookWrapper> expectedWrappers() {
        List<BookWrapper> expectedWrappers = new ArrayList<>();
        expectedWrappers.add(BookWrapper.builder().id(1L).name("test_book1").status("A").build());
        expectedWrappers.add(BookWrapper.builder().id(2L).name("test_book2").status("A").build());
        return expectedWrappers;
    }

    public static Book book() {
        return Book.builder().name("test_book").author("test_author").price(BigDecimal.TEN).build();
    }

    public static Book updatedBook() {
        return Book.builder().id(1L).name("test_book2").author("test_author2").price(BigDecimal.TEN).build();
    }

    public static Book bookWithIdAndStatus() {
        return Book.builder().id(1L).name("test_book").author("test_author").status("A").fkLibrarianLibraryId(1L).build();
    }

    public static Book savedBook() {
        return Book.builder().name("test_book").author("test_author").price(BigDecimal.TEN).build();
    }

    public static List<Book> bookList() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(Book.builder().id(1L).name("test_book").status("A").fkLibrarianLibraryId(1L).build());
        bookList.add(Book.builder().id(2L).name("test_book2").status("A").fkLibrarianLibraryId(1L).build());
        return bookList;
    }

    public static List<BookReadResponse> bookReadResponseList() {
        List<BookReadResponse> expectedResponseList = new ArrayList<>();
        expectedResponseList.add(BookReadResponse.builder().id(1L).name("test_book").author("test_author").status("A").fkLibrarianLibraryId(1L).build());
        expectedResponseList.add(BookReadResponse.builder().id(2L).name("test_book2").author("test_author").status("A").fkLibrarianLibraryId(1L).build());
        return expectedResponseList;
    }

}
