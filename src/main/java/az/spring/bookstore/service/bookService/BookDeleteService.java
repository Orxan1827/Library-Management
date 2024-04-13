package az.spring.bookstore.service.bookService;

import az.spring.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookDeleteService {

    private final BookRepository bookRepository;

    public void deleteBook(){

    }

}
