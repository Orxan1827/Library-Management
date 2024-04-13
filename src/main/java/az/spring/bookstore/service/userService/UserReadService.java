package az.spring.bookstore.service.userService;

import az.spring.bookstore.dto.response.UserReadResponse;
import az.spring.bookstore.entity.User;
import az.spring.bookstore.exception.UserNotFoundException;
import az.spring.bookstore.mapper.UserMapper;
import az.spring.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserReadService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserReadResponse getUser(Long id) {

        User user = findUser(id);
        return userMapper.mapUserReadResponse(user);

    }

    public User findUser(Long id) {
       return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
