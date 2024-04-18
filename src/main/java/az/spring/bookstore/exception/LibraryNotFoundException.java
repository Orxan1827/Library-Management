package az.spring.bookstore.exception;

import org.springframework.http.HttpStatus;

import static az.spring.bookstore.constant.ExceptionConstant.LIBRARY_NOT_FOUND;

public class LibraryNotFoundException extends GenericException{

    public LibraryNotFoundException() {
        super(HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.value(), LIBRARY_NOT_FOUND);
    }

}
