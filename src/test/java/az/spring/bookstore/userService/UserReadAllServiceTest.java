package az.spring.bookstore.userService;

import az.spring.bookstore.dto.response.user.UserReadResponse;
import az.spring.bookstore.entity.User;
import az.spring.bookstore.mapper.UserMapper;
import az.spring.bookstore.repository.UserRepository;
import az.spring.bookstore.service.userService.UserReadAllService;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class UserReadAllServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserReadAllService readAllService;

    @Test
    public void testUserGetAllService_whenCalled_shouldReturnReadResponse() {
        UserReadResponse response = UserUtil.userReadResponse();

        List<UserReadResponse> responseList = List.of(response);

        User user = UserUtil.user();

        when(userRepository.findAll()).thenReturn(List.of(user));
        when(userMapper.mapUserReadResponse(user)).thenReturn(response);

        List<UserReadResponse> result = readAllService.getAllUsers();

        assertEquals(result, responseList);
        assertNotNull(result);

        verify(userRepository, times(1)).findAll();
    }
}
