package az.spring.bookstore.dto.request.user;

import az.spring.bookstore.constraint.Email;
import az.spring.bookstore.constraint.Password;
import az.spring.bookstore.constraint.Username;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {

    @Username
    private String username;

    @Password
    private String password;

    @Email
    private String email;

}
