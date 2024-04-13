package az.spring.bookstore.exception;

import static az.spring.bookstore.enums.ExceptionConstant.BOOK_NOT_FOUND;

public class BookNotFoundException extends GenericException{

    public BookNotFoundException() {
        super(BOOK_NOT_FOUND.getStatus(), BOOK_NOT_FOUND.getCode(),BOOK_NOT_FOUND.getMessage());
    }

}
