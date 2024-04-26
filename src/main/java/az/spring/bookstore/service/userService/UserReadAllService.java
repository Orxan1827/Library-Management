package az.spring.bookstore.service.userService;

import az.spring.bookstore.dto.response.user.UserReadResponse;
import az.spring.bookstore.mapper.UserMapper;
import az.spring.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserReadAllService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public List<UserReadResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapUserReadResponse)
                .collect(Collectors.toList());
    }

}
