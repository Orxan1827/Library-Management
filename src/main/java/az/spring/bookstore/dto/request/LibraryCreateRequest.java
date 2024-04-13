package az.spring.bookstore.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCreateRequest {

    private Long userId;

}
