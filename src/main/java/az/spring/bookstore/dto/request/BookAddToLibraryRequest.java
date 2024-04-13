package az.spring.bookstore.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookAddToLibraryRequest {

    private List<Long> bookId;
    private Long userId;

}
