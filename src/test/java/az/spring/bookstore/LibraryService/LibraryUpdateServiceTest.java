package az.spring.bookstore.LibraryService;

import az.spring.bookstore.dto.request.library.LibraryReadRequest;
import az.spring.bookstore.dto.request.library.LibraryUpdateRequest;
import az.spring.bookstore.entity.Library;
import az.spring.bookstore.mapper.LibraryMapper;
import az.spring.bookstore.repository.LibraryRepository;
import az.spring.bookstore.service.libraryService.LibraryReadService;
import az.spring.bookstore.service.libraryService.LibraryUpdateService;
import az.spring.bookstore.util.LibraryUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class LibraryUpdateServiceTest {

    @Mock
    private LibraryReadService readService;

    @Mock
    private LibraryRepository libraryRepository;

    @Mock
    private LibraryMapper libraryMapper;

    @InjectMocks
    private LibraryUpdateService updateService;

    @Test
    public void testLibraryUpdateService_whenLibraryExistsById() {
        Long libraryId = 1L;
        Library library = LibraryUtil.library();
        Library upLibrary = LibraryUtil.updatedLibrary();
        LibraryUpdateRequest updateRequest = LibraryUpdateRequest.builder().libraryId(1L).name("test_library").build();

        when(readService.findLibrary(libraryId)).thenReturn(library);
        when(libraryMapper.updateLibraryFromUpdateRequest(updateRequest, library)).thenReturn(upLibrary);
        when(libraryRepository.save(upLibrary)).thenReturn(upLibrary);

        updateService.updateLibrary(updateRequest);

        verify(readService,times(1)).findLibrary(libraryId);
        verify(libraryMapper, times(1)).updateLibraryFromUpdateRequest(updateRequest, library);
        verify(libraryRepository,times(1)).save(upLibrary);
    }

    @Test
    public void testLibraryUpdateService_whenLibraryDoesExistsById() {
        Long libraryId = 1L;
        Library library = LibraryUtil.library();
        Library upLibrary = LibraryUtil.updatedLibrary();
        LibraryUpdateRequest updateRequest = LibraryUpdateRequest.builder().libraryId(1L).name("test_library").build();

        when(readService.findLibrary(libraryId)).thenReturn(null);

        updateService.updateLibrary(updateRequest);


        verify(readService,times(1)).findLibrary(libraryId);
        verify(libraryMapper, never()).updateLibraryFromUpdateRequest(updateRequest, library);
        verify(libraryRepository,never()).save(upLibrary);
    }
}
