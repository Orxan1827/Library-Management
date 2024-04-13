package az.spring.bookstore.repository;

import az.spring.bookstore.entity.Book;
import az.spring.bookstore.wrapper.BookWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("""
             select new az.spring.bookstore.wrapper.BookWrapper(b.id,b.name,b.status) from Book b where b.status = 'A'
            """)
    List<BookWrapper> findByBookStatusA();

}
