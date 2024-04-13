package az.spring.bookstore.exception;

import static az.spring.bookstore.enums.ExceptionConstant.LIBRARY_NOT_FOUND;
public class LibraryNotFoundException extends GenericException{

    public LibraryNotFoundException() {
        super(LIBRARY_NOT_FOUND.getStatus(), LIBRARY_NOT_FOUND.getCode(),LIBRARY_NOT_FOUND.getMessage());
    }

}
