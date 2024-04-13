package az.spring.bookstore.service.userService;

import az.spring.bookstore.dto.request.UserCreateRequest;
import az.spring.bookstore.dto.response.UserCreateResponse;
import az.spring.bookstore.entity.Library;
import az.spring.bookstore.entity.User;
import az.spring.bookstore.exception.GenericException;
import az.spring.bookstore.mapper.UserMapper;
import az.spring.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        log.info("User created {}",user);
        return userMapper.mapUserToResponse(savedUser);

    }

    @Transactional
    public UserCreateResponse create2(UserCreateRequest createRequest){
        userCheck(createRequest.getUsername());
        Library library = new Library();
        User user = User.builder()
                .email(createRequest.getEmail())
                .username(createRequest.getUsername())
                .password(createRequest.getPassword())
                .library(library)
                .build();
        library.setUser(user);
        User savedUser = userRepository.save(user);
        return userMapper.mapUserToResponse(savedUser);
    }

    protected void userCheck(String username) {

        var isAllReadyRegistered = userRepository.existsByUsername(username);
        if (isAllReadyRegistered) {
            throw GenericException
                    .builder()
                    .httpStatus(HttpStatus.FOUND)
                    .errorMessage("Username: " + username + "is already used")
                    .errorCode("409")
                    .build();
        }

    }
}
