package az.spring.bookstore.service.bookService;

import az.spring.bookstore.dto.request.book.BookCreateRequest;
import az.spring.bookstore.dto.response.book.BookCreateResponse;
import az.spring.bookstore.mapper.BookMapper;
import az.spring.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookCreateService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    public BookCreateResponse createBook(BookCreateRequest createRequest) {
       return bookMapper.mapEntityToBookCreateResponse(bookRepository.save(bookMapper.mapCreateRequestToEntity(createRequest)));
    }

}
