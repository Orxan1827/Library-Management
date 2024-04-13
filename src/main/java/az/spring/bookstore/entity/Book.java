package az.spring.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String status;

    private Long fkLibraryId;

    private Long fkUserId;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void status() {
        if (status == null) {
            status = "C";
        }
    }

}
