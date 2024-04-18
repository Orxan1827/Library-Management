package az.spring.bookstore.service.bookService;

import az.spring.bookstore.dto.request.book.BookDeleteRequest;
import az.spring.bookstore.entity.Book;
import az.spring.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookDeleteService {

    private final BookRepository bookRepository;

    private final BookReadService readService;

    public void deleteBook(BookDeleteRequest deleteRequest){
        bookRepository.delete(readService.findBook(deleteRequest.getBookId()));
    }

    public void deActiveBook(BookDeleteRequest deleteRequest){
        Book book = readService.findBook(deleteRequest.getBookId());
        book.setStatus("D");
        bookRepository.save(book);
    }

}
