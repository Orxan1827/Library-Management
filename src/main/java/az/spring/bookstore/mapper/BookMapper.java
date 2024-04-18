package az.spring.bookstore.mapper;

import az.spring.bookstore.dto.request.book.BookCreateRequest;
import az.spring.bookstore.dto.request.book.BookUpdateRequest;
import az.spring.bookstore.dto.response.book.BookCreateResponse;
import az.spring.bookstore.dto.response.book.BookReadResponse;
import az.spring.bookstore.dto.response.book.BookUpdatedResponse;
import az.spring.bookstore.entity.Book;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    Book mapCreateRequestToEntity(BookCreateRequest createRequest);

    BookCreateResponse mapEntityToBookCreateResponse(Book book);

    BookReadResponse mapBookReadResponse(Book book);

    BookUpdatedResponse mapBookToUpdatedResponse(Book book);

    @Mapping(target = "id", ignore = true)
    Book updateBookFromUpdateRequest(BookUpdateRequest updateRequest, @MappingTarget Book book);


}
