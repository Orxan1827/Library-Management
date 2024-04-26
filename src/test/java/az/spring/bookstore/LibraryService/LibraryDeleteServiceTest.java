package az.spring.bookstore.LibraryService;

import az.spring.bookstore.dto.request.library.LibraryDeleteRequest;
import az.spring.bookstore.entity.Library;
import az.spring.bookstore.exception.LibraryNotFoundException;
import az.spring.bookstore.repository.LibraryRepository;
import az.spring.bookstore.service.libraryService.LibraryDeleteService;
import az.spring.bookstore.service.libraryService.LibraryReadService;
import az.spring.bookstore.util.LibraryUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class LibraryDeleteServiceTest {

    @Mock
    private  LibraryRepository libraryRepository;

    @Mock
    private  LibraryReadService readService;

    @InjectMocks
    private LibraryDeleteService libraryDeleteService;

    @Test
    public void testLibraryDeleteService_whenDeleteRequest_shouldDeleteLibrary() {
        Long libraryId = 1L;
        LibraryDeleteRequest request = new LibraryDeleteRequest(libraryId);
        Library library = LibraryUtil.library();

        when(readService.findLibrary(libraryId)).thenReturn(library);

        libraryDeleteService.deleteLibrary(request);

        verify(readService, times(1)).findLibrary(libraryId);
        verify(libraryRepository, times(1)).delete(library);
    }

    @Test
    public void testLibraryDeleteService_whenLibraryDoesNotExists_shouldReturnLibraryNotFoundException() {
        Long libraryId = 1L;
        LibraryDeleteRequest request = new LibraryDeleteRequest(libraryId);
        Library library = LibraryUtil.library();

        when(readService.findLibrary(libraryId)).thenThrow(LibraryNotFoundException.class);

        assertThrows(LibraryNotFoundException.class, () -> libraryDeleteService.deleteLibrary(request));

        verify(readService, times(1)).findLibrary(libraryId);
        verify(libraryRepository, never()).delete(library);
    }
}
