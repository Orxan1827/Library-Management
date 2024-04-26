package az.spring.bookstore.service.bookService;

import az.spring.bookstore.dto.request.book.BookUpdateRequest;
import az.spring.bookstore.dto.response.book.BookUpdatedResponse;
import az.spring.bookstore.entity.Book;
import az.spring.bookstore.entity.User;
import az.spring.bookstore.exception.UnauthorizedAccessException;
import az.spring.bookstore.mapper.BookMapper;
import az.spring.bookstore.repository.BookRepository;
import az.spring.bookstore.service.userService.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static az.spring.bookstore.enums.Role.LIBRARIAN;

@Service
@RequiredArgsConstructor
public class BookUpdateService {

    private final BookRepository bookRepository;

    private final BookReadService readService;

    private final UserReadService userReadService;

    private final BookMapper bookMapper;

    public BookUpdatedResponse updateBook(BookUpdateRequest updateRequest) {
        User user = userReadService.findUser(updateRequest.getUserId());
        if (!LIBRARIAN.equals(user.getRole())) {
            throw new UnauthorizedAccessException();
        }
        Book updatedBook = bookMapper.updateBookFromUpdateRequest(updateRequest, readService.findBook(updateRequest.getId()));
        return bookMapper.mapBookToUpdatedResponse(bookRepository.save(updatedBook));
    }

}
