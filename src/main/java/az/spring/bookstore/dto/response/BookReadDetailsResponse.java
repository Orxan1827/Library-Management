package az.spring.bookstore.dto.response;

import az.spring.bookstore.dto.dto.LibraryDto;
import az.spring.bookstore.dto.dto.UserDto;
import az.spring.bookstore.entity.Book;
import az.spring.bookstore.enums.BookStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookReadDetailsResponse {

    private Long id;
    private String name;
    private LibraryDto libraryDto;
    private UserDto userDto;

    public static BookReadDetailsResponse mapBookToResponseDetails(Book book) {

       return BookReadDetailsResponse
                .builder()
                .id(book.getId())
                .name(book.getName())
                .libraryDto(LibraryDto.builder()
                        .id(book.getLibrary().getId())
                        .build())
                .userDto(UserDto.builder()
                        .username(book.getUser().getUsername())
                        .build())
                .build();

    }
}
