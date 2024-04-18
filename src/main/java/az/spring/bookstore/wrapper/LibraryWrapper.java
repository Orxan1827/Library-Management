package az.spring.bookstore.wrapper;

import lombok.Data;

@Data
public class LibraryWrapper {

    private Long id;

    private String name;

    private Long fkBookId;

    private Long fkUserId;

    private String status;

    public LibraryWrapper(Long id, String name, Long fkBookId, Long fkUserId, String status) {
        this.id = id;
        this.name = name;
        this.fkBookId = fkBookId;
        this.fkUserId = fkUserId;
        this.status = status;
    }
}
