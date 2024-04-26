package az.spring.bookstore.LibraryService;

import az.spring.bookstore.dto.request.library.LibraryReadRequest;
import az.spring.bookstore.dto.response.library.LibraryReadResponse;
import az.spring.bookstore.entity.Library;
import az.spring.bookstore.exception.LibraryNotFoundException;
import az.spring.bookstore.mapper.LibraryMapper;
import az.spring.bookstore.repository.LibraryRepository;
import az.spring.bookstore.service.libraryService.LibraryReadService;
import az.spring.bookstore.util.LibraryUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class LibraryReadServiceTest {

    @Mock
    private LibraryRepository libraryRepository;

    @Mock
    private LibraryMapper libraryMapper;

    @InjectMocks
    private LibraryReadService libraryReadService;

    @Test
    public void testLibraryReadService_whenLibraryExistsById_shouldReturnLibrary() {
        Long libraryId = 1L;
        Library expected = LibraryUtil.library();
        when(libraryRepository.findById(libraryId)).thenReturn(Optional.of(expected));

        Library actual = libraryReadService.findLibrary(libraryId);

        assertEquals(actual, expected);
        assertNotNull(actual);

        verify(libraryRepository, times(1)).findById(libraryId);
    }

    @Test
    public void testLibraryReadService_whenLibraryDoesNotExistsById_shouldReturnLibNotFoundException() {
        Long libraryId = 1L;
        when(libraryRepository.findById(libraryId)).thenReturn(Optional.empty());

        assertThrows(LibraryNotFoundException.class, () -> libraryReadService.findLibrary(libraryId));

        verify(libraryRepository, times(1)).findById(libraryId);
    }

    @Test
    public void testLibraryReadService_whenLibraryReadRequest_shouldReturnLibraryReadResponse() {
        Long libraryId = 1L;
        Library expected = LibraryUtil.library();
        LibraryReadRequest request = LibraryUtil.readRequest();
        LibraryReadResponse response = LibraryUtil.readResponse();
        when(libraryRepository.findById(libraryId)).thenReturn(Optional.of(expected));
        when(libraryMapper.mapEntityToReadResponse(expected)).thenReturn(response);

        LibraryReadResponse actual = libraryReadService.getLibrary(request);

        assertEquals(actual, response);
        assertNotNull(actual);

        verify(libraryRepository, times(1)).findById(libraryId);
        verify(libraryMapper, times(1)).mapEntityToReadResponse(expected);
    }
}
