package az.spring.bookstore.repository;

import az.spring.bookstore.dto.request.library.LibraryReadBookIdRequest;
import az.spring.bookstore.entity.Library;
import az.spring.bookstore.wrapper.LibraryWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Long> {

    boolean existsLibraryById(Long id);

    boolean existsLibraryByFkUserId(Long userId);

    Library findByNameOrFkUserIdAndStatusIn(String name, Long fkUserId, List<String> status);

    @Query(value = """
             select new az.spring.bookstore.wrapper.LibraryWrapper(l.id,l.name,l.fkUserId,l.status) from Library l where l.status = 'A'
            """)
    List<LibraryWrapper> findByLibraryStatusA();

}
