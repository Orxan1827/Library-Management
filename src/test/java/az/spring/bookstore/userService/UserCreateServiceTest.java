package az.spring.bookstore.userService;


import az.spring.bookstore.dto.request.user.UserCreateRequest;
import az.spring.bookstore.dto.response.user.UserCreateResponse;
import az.spring.bookstore.entity.User;
import az.spring.bookstore.exception.GenericException;
import az.spring.bookstore.mapper.UserMapper;
import az.spring.bookstore.repository.UserRepository;
import az.spring.bookstore.service.userService.UserCreateService;
import az.spring.bookstore.util.UserUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class UserCreateServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserCreateService userCreateService;

    @Test
    public void testUserCreateService_whenCreateRequest_shouldReturnUserCreateResponse() {
        UserCreateResponse response = UserUtil.createResponse();

        UserCreateRequest request = UserUtil.createRequest();

        User user = UserUtil.user();

        User savedUser = UserUtil.savedUser();

        when(userMapper.mapRequestToUser(request)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userMapper.mapUserToResponse(savedUser)).thenReturn(response);

        UserCreateResponse result = userCreateService.createUser(request);
        assertEquals(result, response);
        assertNotNull(result);

        verify(userRepository, times(1)).existsByUsername(request.getUsername());
        verify(userMapper, times(1)).mapRequestToUser(request);
        verify(userRepository, times(1)).save(user);
        verify(userMapper, times(1)).mapUserToResponse(savedUser);

    }

    @Test
    public void testUserCreateService_whenCreateRequest_shouldReturnUserUserAllReadyExistsException() {
        UserCreateRequest request = UserUtil.createRequest();

        User user = UserUtil.user();

        User savedUser = UserUtil.savedUser();

        when(userRepository.existsByUsername(request.getUsername())).thenReturn(true);

        assertThrows(GenericException.class, () -> userCreateService.createUser(request));
        verify(userRepository, times(1)).existsByUsername(request.getUsername());
        verify(userRepository, never()).save(user);
        verify(userMapper, never()).mapRequestToUser(request);
        verify(userMapper, never()).mapUserToResponse(savedUser);
    }

}
