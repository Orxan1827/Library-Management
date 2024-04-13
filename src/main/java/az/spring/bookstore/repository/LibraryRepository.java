package az.spring.bookstore.repository;

import az.spring.bookstore.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {

    boolean existsLibraryById(Long id);

    boolean existsLibraryByFkUserId(Long userId);

}
