package az.spring.bookstore.dto.response.library;

import lombok.*;

@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCreateResponse {

    private Long id;

    private String name;

    private Long fkBookId;

    private Long fkUserId;

    private String status;

}
