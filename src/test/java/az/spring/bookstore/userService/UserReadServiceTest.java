package az.spring.bookstore.userService;

import az.spring.bookstore.dto.response.user.UserReadResponse;
import az.spring.bookstore.entity.User;
import az.spring.bookstore.exception.UserNotFoundException;
import az.spring.bookstore.mapper.UserMapper;
import az.spring.bookstore.repository.UserRepository;
import az.spring.bookstore.service.userService.UserReadService;
import az.spring.bookstore.util.UserUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class UserReadServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserReadService userReadService;

    @Test
    public void testUserRaedService_whenUserExists_shouldReturnUserReadResponse() {
        User user = UserUtil.userWithId();
        UserReadResponse response = UserUtil.userReadResponseId();

        when(userMapper.mapUserReadResponse(user)).thenReturn(response);

        assertEquals(user.getId(), response.getId());

        verify(userMapper, times(0)).mapUserReadResponse(user);
    }

    @Test
    public void testUserFindService_whenUserExists_shouldReturnUser() {
        User user = UserUtil.userWithId();

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        User result = userReadService.findUser(user.getId());

        assertEquals(result, user);
        assertNotNull(result);

        verify(userRepository, times(1)).findById(user.getId());

    }

    @Test
    public void testUserFindService_whenUserDoesNotExists_shouldThrowsUserNotFoundException() {
        User user = UserUtil.userWithId();

        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userReadService.findUser(user.getId()));

        verify(userRepository, times(1)).findById(user.getId());
    }

}
