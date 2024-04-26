package az.spring.bookstore.dto.request.library;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibraryUpdateRequest {

    @NotNull
    private Long libraryId;

    @NotBlank
    private String name;

}
