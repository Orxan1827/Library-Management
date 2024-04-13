package az.spring.bookstore.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookAddToLibraryRequest {

    private Long bookId;
    private Long userId;

}
