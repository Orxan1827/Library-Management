package az.spring.bookstore.service.userService;

import az.spring.bookstore.dto.request.UserDeleteRequest;
import az.spring.bookstore.entity.User;
import az.spring.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDeleteService {

    private final UserRepository userRepository;

    private final UserReadService userReadService;

    public void deleteUser(UserDeleteRequest deleteRequest) {
        User user = userReadService.findUser(deleteRequest.getId());
        userRepository.delete(user);
    }
}
