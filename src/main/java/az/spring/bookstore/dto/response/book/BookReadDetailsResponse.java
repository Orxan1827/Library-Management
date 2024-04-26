package az.spring.bookstore.dto.response.book;

import az.spring.bookstore.entity.Book;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookReadDetailsResponse {

    private Long id;
    private String name;
    private String author;
    private String status;
    private BigDecimal price;
    private Long fkLibrarianLibraryId;
    private Long fkStudentLibraryId;
    private Long fkLibrarianUserId;
    private Long fkStudentUserId;

    public static BookReadDetailsResponse mapBookToResponseDetails(Book book) {

        return BookReadDetailsResponse
                .builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .status(book.getStatus())
                .price(book.getPrice())
                .fkLibrarianLibraryId(book.getFkLibrarianLibraryId())
                .fkStudentLibraryId(book.getFkStudentLibraryId())
                .fkLibrarianUserId(book.getFkLibrarianUserId())
                .fkStudentUserId(book.getFkStudentUserId())
                .build();
    }

}
