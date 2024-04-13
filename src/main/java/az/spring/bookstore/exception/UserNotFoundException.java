package az.spring.bookstore.exception;

import static az.spring.bookstore.enums.ExceptionConstant.USER_NOT_FOUND;
public class UserNotFoundException extends GenericException {

    public UserNotFoundException() {
        super(USER_NOT_FOUND.getStatus(), USER_NOT_FOUND.getCode(),USER_NOT_FOUND.getMessage());
    }

}
