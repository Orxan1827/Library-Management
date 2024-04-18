package az.spring.bookstore.dto.request.library;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCreateRequest {

    @NotNull
    private Long fkUserId;

    @NotBlank
    private String name;

}
