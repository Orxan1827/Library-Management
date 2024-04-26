package az.spring.bookstore.util;

import az.spring.bookstore.dto.request.book.BookAddToLibraryRequest;
import az.spring.bookstore.dto.request.library.LibraryCreateRequest;
import az.spring.bookstore.dto.request.library.LibraryReadRequest;
import az.spring.bookstore.dto.response.library.LibraryCreateResponse;
import az.spring.bookstore.dto.response.library.LibraryReadResponse;
import az.spring.bookstore.entity.Library;
import az.spring.bookstore.wrapper.LibraryWrapper;

import java.util.ArrayList;
import java.util.List;

public class LibraryUtil {

    private LibraryUtil() {

    }
    public static LibraryCreateResponse createResponse() {
        return LibraryCreateResponse.builder()
                .id(1L)
                .fkUserId(1L)
                .name("test_library")
                .build();
    }

    public static LibraryCreateRequest createRequest() {
        return LibraryCreateRequest.builder()
                .fkUserId(1L)
                .name("test_library")
                .build();
    }


    public static Library library() {
        return Library.builder()
                .id(1L)
                .fkUserId(1L)
                .name("test_library")
                .build();
    }

    public static Library librarianLibrary() {
        return Library.builder()
                .id(2L)
                .fkUserId(2L)
                .name("test_library")
                .build();
    }

    public static Library updatedLibrary() {
        return Library.builder()
                .id(1L)
                .fkUserId(1L)
                .name("test_library_2")
                .build();
    }

    public static List<Library> libraryList() {
        List<Library> libraries = new ArrayList<>();
        libraries.add(Library.builder().id(1L).name("test_library_1").build());
        libraries.add(Library.builder().id(2L).name("test_library_2").build());
        return libraries;
    }

    public static List<LibraryReadResponse> readResponsesList() {
        List<LibraryReadResponse> expectedResponses = new ArrayList<>();
        expectedResponses.add(LibraryReadResponse.builder().libraryId(1L).name("test_library_1").build());
        expectedResponses.add(LibraryReadResponse.builder().libraryId(2L).name("test_library_2").build());
        return expectedResponses;
    }

    public static List<LibraryWrapper> expectedWrappers() {
        List<LibraryWrapper> expectedWrappers = new ArrayList<>();
        expectedWrappers.add(LibraryWrapper.builder().id(1L).name("test_library_1").build());
        expectedWrappers.add(LibraryWrapper.builder().id(2L).name("test_library_2").build());
        return expectedWrappers;
    }

    public static LibraryReadRequest readRequest() {
        return LibraryReadRequest.builder()
                .libraryId(1L)
                .build();
    }

    public static LibraryReadResponse readResponse() {
        return LibraryReadResponse.builder()
                .libraryId(1L)
                .build();
    }

    public static BookAddToLibraryRequest BookAddToLibraryRequest() {
        return BookAddToLibraryRequest.builder()
                .fkBookId(1L)
                .fkUserId(1L)
                .build();
    }
}
