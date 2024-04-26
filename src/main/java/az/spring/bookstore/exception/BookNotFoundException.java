package az.spring.bookstore.exception;

import org.springframework.http.HttpStatus;

import static az.spring.bookstore.constant.ExceptionConstant.BOOK_NOT_FOUND;

public class BookNotFoundException extends GenericException{

    public BookNotFoundException() {
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), BOOK_NOT_FOUND);
    }

}
