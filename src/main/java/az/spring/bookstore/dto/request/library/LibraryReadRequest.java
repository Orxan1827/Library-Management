package az.spring.bookstore.dto.request.library;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibraryReadRequest {

    @NotNull
    private Long libraryId;

}
