package az.spring.bookstore.service.userService;

import az.spring.bookstore.entity.User;
import az.spring.bookstore.exception.UserNotFoundException;
import az.spring.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService {

    private final UserRepository userRepository;

    public String login(String username, String password) {
       User foundedUser = userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
       if (foundedUser.getPassword().equals(password)){
           return "User login successfully!";
       }
        return "Invalid username or password";
    }

}
