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
public class BookUpdateRequest {

    @NotNull
    private Long id;

    @NotNull
    private String status;

    @NotNull
    private BigDecimal price;

}
