package az.spring.bookstore.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionConstant {

    USER_NOT_FOUND("User not found with given id","404", HttpStatus.NOT_FOUND.name()),
    BOOK_NOT_FOUND("Book not found with given id","404", HttpStatus.NOT_FOUND.name()),
    LIBRARY_NOT_FOUND("Library not found with given id","404", HttpStatus.NOT_FOUND.name());

    private String message;
    private String code;
    private HttpStatus status;

    ExceptionConstant(String message, String code, String status) {
        this.message = message;
        this.code = code;
        this.status = HttpStatus.valueOf(status);
    }

}
