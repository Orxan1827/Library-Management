package az.spring.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "books")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String author;

    private BigDecimal price;
    private String status;

    private Long fkLibraryId;

    private Long fkUserId;

    @PrePersist
    public void saveStatus() {
        if (status == null) {
            status = "C";
        }
    }

}
