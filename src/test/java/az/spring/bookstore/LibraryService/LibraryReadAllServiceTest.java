package az.spring.bookstore.LibraryService;

import az.spring.bookstore.dto.response.library.LibraryReadResponse;
import az.spring.bookstore.entity.Library;
import az.spring.bookstore.mapper.LibraryMapper;
import az.spring.bookstore.repository.LibraryRepository;
import az.spring.bookstore.service.libraryService.LibraryReadAllService;
import az.spring.bookstore.util.LibraryUtil;
import az.spring.bookstore.wrapper.LibraryWrapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class LibraryReadAllServiceTest {

    @Mock
    private LibraryRepository libraryRepository;

    @Mock
    private LibraryMapper libraryMapper;

    @InjectMocks
    private LibraryReadAllService readAllService;

    @Test
    public void testLibraryReadAllService_whenGetAllLibraryCalled_shouldReturnLibraryReadResponse() {
        List<Library> libraryList = LibraryUtil.libraryList();
        List<LibraryReadResponse> expectedResponses = LibraryUtil.readResponsesList();

        when(libraryRepository.findAll()).thenReturn(libraryList);
        when(libraryMapper.mapEntityToReadResponse(any(Library.class))).thenReturn(expectedResponses.get(0), expectedResponses.get(1));

        List<LibraryReadResponse> actualResponses = readAllService.getAllLibrary();

        assertEquals(expectedResponses.size(), actualResponses.size());
        assertNotNull(actualResponses);

        verify(libraryRepository, times(1)).findAll();
        verify(libraryMapper, times(2)).mapEntityToReadResponse(any());
    }

    @Test
    public void testLibraryReadAllService_whenFindByLibraryStatusA_ShouldReturnLibraryWrapper() {
        List<LibraryWrapper> expectedWrappers = LibraryUtil.expectedWrappers();

        when(libraryRepository.findByLibraryStatusA()).thenReturn(expectedWrappers);

        List<LibraryWrapper> actualWrappers = readAllService.findByLibraryStatusA();

        assertEquals(expectedWrappers.size(), actualWrappers.size());
        assertEquals(expectedWrappers, actualWrappers);
        assertEquals(expectedWrappers.get(0).getStatus(), actualWrappers.get(0).getStatus());
        assertEquals(expectedWrappers.get(1).getStatus(), actualWrappers.get(1).getStatus());
        assertNotNull(actualWrappers);

        verify(libraryRepository, times(1)).findByLibraryStatusA();
    }
}
