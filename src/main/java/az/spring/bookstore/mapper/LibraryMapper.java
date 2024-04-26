package az.spring.bookstore.mapper;

import az.spring.bookstore.dto.request.book.BookUpdateRequest;
import az.spring.bookstore.dto.request.library.LibraryCreateRequest;
import az.spring.bookstore.dto.request.library.LibraryUpdateRequest;
import az.spring.bookstore.dto.response.library.LibraryCreateResponse;
import az.spring.bookstore.dto.response.library.LibraryReadResponse;
import az.spring.bookstore.entity.Book;
import az.spring.bookstore.entity.Library;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LibraryMapper {

   Library mapCreateRequestToEntity(LibraryCreateRequest createRequest);

   LibraryCreateResponse mapEntityToCreateResponse(Library library);

   LibraryReadResponse mapEntityToReadResponse(Library library);

   @Mapping(target = "id", ignore = true)
   Library updateLibraryFromUpdateRequest(LibraryUpdateRequest updateRequest, @MappingTarget Library library);

}
