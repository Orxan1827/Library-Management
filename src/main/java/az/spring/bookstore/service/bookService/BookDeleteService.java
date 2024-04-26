package az.spring.bookstore.service.bookService;

import az.spring.bookstore.dto.request.book.BookDeleteRequest;
import az.spring.bookstore.entity.Book;
import az.spring.bookstore.entity.User;
import az.spring.bookstore.exception.UnauthorizedAccessException;
import az.spring.bookstore.repository.BookRepository;
import az.spring.bookstore.service.userService.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static az.spring.bookstore.enums.Role.LIBRARIAN;

@Service
@RequiredArgsConstructor
public class BookDeleteService {

    private final BookRepository bookRepository;

    private final BookReadService readService;

    private final UserReadService userReadService;

    public void deleteBook(BookDeleteRequest deleteRequest) {
        User user = userReadService.findUser(deleteRequest.getUserId());
        if (!LIBRARIAN.equals(user.getRole())) {
            throw new UnauthorizedAccessException();
        }
        bookRepository.delete(readService.findBook(deleteRequest.getBookId()));
    }

    public void deActiveBook(BookDeleteRequest deleteRequest) {
        Book book = readService.findBook(deleteRequest.getBookId());
        book.setStatus("D");
        bookRepository.save(book);
    }

}
