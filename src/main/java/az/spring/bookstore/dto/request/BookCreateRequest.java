package az.spring.bookstore.dto.request;

import az.spring.bookstore.entity.User;
import az.spring.bookstore.enums.BookStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateRequest {

//    @NotNull
//    private Long userId;
//
//    @NotNull
//    private Long libraryId;

    @NotBlank
    private String name;

}
