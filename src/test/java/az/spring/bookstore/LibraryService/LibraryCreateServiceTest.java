package az.spring.bookstore.LibraryService;

import az.spring.bookstore.dto.request.library.LibraryCreateRequest;
import az.spring.bookstore.dto.request.user.UserCreateRequest;
import az.spring.bookstore.dto.response.library.LibraryCreateResponse;
import az.spring.bookstore.dto.response.user.UserCreateResponse;
import az.spring.bookstore.entity.Library;
import az.spring.bookstore.entity.User;
import az.spring.bookstore.exception.GenericException;
import az.spring.bookstore.exception.UserNotFoundException;
import az.spring.bookstore.mapper.LibraryMapper;
import az.spring.bookstore.mapper.UserMapper;
import az.spring.bookstore.repository.LibraryRepository;
import az.spring.bookstore.repository.UserRepository;
import az.spring.bookstore.service.libraryService.LibraryCreateService;
import az.spring.bookstore.service.userService.UserCreateService;
import az.spring.bookstore.service.userService.UserReadService;
import az.spring.bookstore.util.LibraryUtil;
import az.spring.bookstore.util.UserUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class LibraryCreateServiceTest {

    @Mock
    private  LibraryRepository libraryRepository;

    @Mock
    private  LibraryMapper libraryMapper;

    @Mock
    private  UserReadService readService;

    @Mock
    private  UserRepository userRepository;

    @InjectMocks
    private LibraryCreateService libraryCreateService;

    @Test
    public void testLibraryCreateService_whenCreateRequest_shouldReturnUserCreateResponse() {
        LibraryCreateResponse response = LibraryUtil.createResponse();

        LibraryCreateRequest request = LibraryUtil.createRequest();

        User user = UserUtil.user();

        Library library = LibraryUtil.library();

        when(readService.findUser(request.getFkUserId())).thenReturn(user);
        when(libraryMapper.mapCreateRequestToEntity(request)).thenReturn(library);
        when(libraryRepository.save(library)).thenReturn(library);
        when(userRepository.save(user)).thenReturn(user);
        when(libraryMapper.mapEntityToCreateResponse(library)).thenReturn(response);

        LibraryCreateResponse actualResponse = libraryCreateService.createLibrary(request);
        assertEquals(actualResponse, response);
        assertNotNull(actualResponse);

        verify(readService, times(1)).findUser(request.getFkUserId());
        verify(libraryRepository, times(1)).save(any());
        verify(libraryMapper, times(1)).mapCreateRequestToEntity(request);
        verify(libraryMapper, times(1)).mapEntityToCreateResponse(library);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testLibraryCreateService_whenUserNotFound_shouldReturnUserNotFoundException() {
        Long userId = 1L;
        LibraryCreateRequest request = new LibraryCreateRequest(userId, "Library Name");
        when(readService.findUser(userId)).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> libraryCreateService.createLibrary(request));

        verify(readService, times(1)).findUser(userId);
        verify(libraryRepository, never()).save(any());
        verify(libraryMapper, never()).mapCreateRequestToEntity(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void testLibraryCreateService_whenLibraryAllReadyExists_shouldReturnLibraryFoundException() {
            LibraryCreateRequest createRequest = LibraryUtil.createRequest();
            Library existingLibrary = LibraryUtil.library();
            when(libraryRepository.findByNameOrFkUserIdAndStatusIn(createRequest.getName(), createRequest.getFkUserId(), List.of("C", "A")))
                    .thenReturn(existingLibrary);

            assertThrows(GenericException.class, () -> libraryCreateService.checkLibraryExisting(createRequest));
    }
}
