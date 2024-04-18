package az.spring.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Table(name = "library")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long fkBookId;

    private Long fkUserId;

    private String status;


    @Column(nullable = false, unique = true)
    @ElementCollection
    private List<Long> fkBookIds;

    @PrePersist
    public void saveStatus() {
        if (status == null) {
            status = "C";
        }
    }

}
