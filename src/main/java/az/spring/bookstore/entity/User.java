package az.spring.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "users")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;
    private String password;
    private String email;

    private Long fkLibraryId;

    private Long fkBookId;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Library library;

    @OneToMany(mappedBy = "user")
    private List<Book> books;

}
