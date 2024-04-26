package az.spring.bookstore.dto.response.user;

import az.spring.bookstore.enums.Role;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateResponse {

    private Long id;
    private String username;
    private String password;
    private String email;
    private Role role;

}
