package az.spring.bookstore.dto.response.library;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibraryReadResponse {

    private Long id;

    private String name;

    private Long fkBookId;

    private Long fkUserId;

    private String status;

}
