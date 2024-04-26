package az.spring.bookstore.wrapper;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LibraryWrapper {

    private Long id;

    private String name;

    private Long fkUserId;

    private String status;

    public LibraryWrapper(Long id, String name, Long fkUserId, String status) {
        this.id = id;
        this.name = name;
        this.fkUserId = fkUserId;
        this.status = status;
    }
}
