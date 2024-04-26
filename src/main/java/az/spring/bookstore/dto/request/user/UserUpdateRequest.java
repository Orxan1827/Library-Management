package az.spring.bookstore.dto.request.user;

import az.spring.bookstore.constraint.Email;
import az.spring.bookstore.constraint.Password;
import az.spring.bookstore.constraint.Username;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    @NotBlank
    private Long id;

    @Username
    private String username;

    @Password
    private String password;

    @Email
    private String email;

}
