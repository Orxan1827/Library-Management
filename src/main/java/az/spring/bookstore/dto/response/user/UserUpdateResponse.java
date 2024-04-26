package az.spring.bookstore.dto.response.user;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateResponse {

    private Long id;
    private String username;
    private String password;
    private String email;

}
