package az.spring.bookstore.service.userService;

import az.spring.bookstore.dto.request.user.UserCreateRequest;
import az.spring.bookstore.dto.response.user.UserCreateResponse;
import az.spring.bookstore.entity.User;
import az.spring.bookstore.exception.GenericException;
import az.spring.bookstore.mapper.UserMapper;
import az.spring.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCreateService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserCreateResponse createUser(UserCreateRequest createRequest) {
        userCheck(createRequest.getUsername());
        User user = userMapper.mapRequestToUser(createRequest);
        User savedUser = userRepository.save(user);
        log.info("User created {}", user);
        return userMapper.mapUserToResponse(savedUser);
    }

    protected void userCheck(String username) {
        var isAllReadyRegistered = userRepository.existsByUsername(username);
        if (isAllReadyRegistered) {
            throw GenericException.builder().httpStatus(FOUND).errorMessage("Username: " + username + " is already used").errorCode(FOUND.value()).build();
        }
    }

}
