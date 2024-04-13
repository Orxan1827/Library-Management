package az.spring.bookstore.wrapper;

import lombok.Data;

@Data
public class BookWrapper {

    private Long id;
    private String name;
    private String status;

    public BookWrapper(Long id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

}
