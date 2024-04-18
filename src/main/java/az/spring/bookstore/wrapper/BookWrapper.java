package az.spring.bookstore.wrapper;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookWrapper {

    private Long id;
    private String name;
    private String status;

    private String author;

    private BigDecimal price;

    public BookWrapper(Long id, String name, String status, String author, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.author = author;
        this.price = price;
    }

}
