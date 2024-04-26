package az.spring.bookstore.userService;

import az.spring.bookstore.dto.request.user.UserUpdateRequest;
import az.spring.bookstore.dto.response.user.UserUpdateResponse;
import az.spring.bookstore.entity.User;
import az.spring.bookstore.exception.UserNotFoundException;
import az.spring.bookstore.mapper.UserMapper;
import az.spring.bookstore.repository.UserRepository;
import az.spring.bookstore.service.userService.UserUpdateService;
import az.spring.bookstore.util.UserUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class UserUpdateServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserUpdateService updateService;

    @Test
    public void testUserUpdateService_whenUpdateRequest_shouldReturnUserUpdateResponse() {
        UserUpdateResponse response = UserUtil.updateResponse();
        UserUpdateRequest request = UserUtil.updateRequest();
        User user = UserUtil.userWithId();
        User updatedUser = UserUtil.updatedUser();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(updatedUser);
        when(userMapper.mapUserToUpdatedResponse(updatedUser)).thenReturn(response);

        UserUpdateResponse result = updateService.update(request);

        assertEquals(response.getUsername(), result.getUsername());
        assertEquals(response.getId(), result.getId());

        assertEquals(response, result);
        assertNotNull(result);

        verify(userRepository, times(1)).findById(user.getId());
        verify(userRepository, times(1)).save(user);
        verify(userMapper, times(1)).mapUserToUpdatedResponse(updatedUser);
    }

    @Test
    public void testUserUpdateService_whenUpdateRequest_shouldReturnUserNotFoundException() {
        UserUpdateRequest request = UserUtil.updateRequest();
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> updateService.update(request));

        verify(userRepository, times(1)).findById(userId);
        verify(userMapper, never()).mapUserToUpdatedResponse(any());
        verify(userRepository, never()).save(any());
    }

}
