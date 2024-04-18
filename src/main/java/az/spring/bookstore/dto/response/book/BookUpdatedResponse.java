package az.spring.bookstore.dto.response.book;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdatedResponse {

    private Long id;

    private String name;

    private String author;

    private BigDecimal price;

    private String status;

    private Long fkLibraryId;

    private Long fkUserId;

}
