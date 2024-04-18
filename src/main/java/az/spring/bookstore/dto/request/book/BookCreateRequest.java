package az.spring.bookstore.dto.request.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String author;

    @NotNull
    private BigDecimal price;

}
