package az.spring.bookstore.dto.request.book;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookReadRequest {

    @NotNull
    private Long fkLibrarianLibraryId;
}
