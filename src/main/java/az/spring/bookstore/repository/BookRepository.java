package az.spring.bookstore.repository;

import az.spring.bookstore.entity.Book;
import az.spring.bookstore.wrapper.BookWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    @Query("""
              select new az.spring.bookstore.wrapper.BookWrapper(b.id,b.name,b.status,b.author,b.price) from Book b where b.status = 'A'
            """)
    List<BookWrapper> findByBookStatusA();

    boolean existsBookByFkLibrarianLibraryIdAndId(Long fkLibrarianLibraryId, Long id);

    Book findBookByFkStudentLibraryId(Long fkStudentLibraryId);

    Optional<Book> findBookByFkLibrarianLibraryId(Long fkLibrarianLibraryId);

}
