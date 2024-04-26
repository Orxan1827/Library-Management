package az.spring.bookstore.userService;

import az.spring.bookstore.dto.request.user.UserDeleteRequest;
import az.spring.bookstore.entity.User;
import az.spring.bookstore.repository.UserRepository;

import az.spring.bookstore.service.userService.UserDeleteService;
import az.spring.bookstore.service.userService.UserReadService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
class UserDeleteServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserReadService userReadService;

    @InjectMocks
    private UserDeleteService userDeleteService;

    @Test
    public void testUserDeleteService_whenDeleteRequest_shouldDeleteUser() {
        Long userId = 1L;
        UserDeleteRequest request = new UserDeleteRequest(1L);

        User user = new User();

        when(userReadService.findUser(userId)).thenReturn(user);

        userDeleteService.deleteUser(request);

        verify(userReadService, times(1)).findUser(request.getId());
        verify(userRepository, times(1)).delete(user);
    }

}
