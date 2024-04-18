package az.spring.bookstore.dto.request.book;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookAddToLibraryRequest {

    @NotNull
    private Long fkBookId;

    @NotNull
    private Long fkUserId;

}
