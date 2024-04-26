package az.spring.bookstore.dto.response.book;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookReadResponse {

    private Long id;
    private String name;
    private String status;
    private String author;
    private BigDecimal price;
    private Long fkLibrarianLibraryId;
    private Long fkStudentLibraryId;
    private Long fkLibrarianUserId;
    private Long fkStudentUserId;

}
