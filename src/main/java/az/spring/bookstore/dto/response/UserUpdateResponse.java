package az.spring.bookstore.dto.response;

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
