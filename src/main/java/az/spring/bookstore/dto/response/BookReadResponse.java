package az.spring.bookstore.dto.response;

import az.spring.bookstore.enums.BookStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookReadResponse {

    private Long id;
    private String name;
    private String status;

}
