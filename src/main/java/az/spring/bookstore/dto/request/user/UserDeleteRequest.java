package az.spring.bookstore.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDeleteRequest {

    @NotBlank
    private Long id;
}
