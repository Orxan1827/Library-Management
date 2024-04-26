package az.spring.bookstore.service.userService;

import az.spring.bookstore.dto.request.user.UserUpdateRequest;
import az.spring.bookstore.dto.response.user.UserUpdateResponse;
import az.spring.bookstore.entity.User;
import az.spring.bookstore.exception.UserNotFoundException;
import az.spring.bookstore.mapper.UserMapper;
import az.spring.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserUpdateService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserUpdateResponse update(UserUpdateRequest updateRequest) {

        User user = userRepository.findById(updateRequest.getId()).orElseThrow(UserNotFoundException::new);
        if (updateRequest.getEmail() != null)
            user.setEmail(updateRequest.getEmail());
        if (updateRequest.getUsername() != null)
            user.setUsername(updateRequest.getUsername());
        if (updateRequest.getPassword() != null)
            user.setPassword(updateRequest.getPassword());
        log.info("User has been updated {}",user);
        User savedUser = userRepository.save(user);
        return userMapper.mapUserToUpdatedResponse(savedUser);
    }

}
