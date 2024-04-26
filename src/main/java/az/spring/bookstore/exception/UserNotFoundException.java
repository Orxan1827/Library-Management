package az.spring.bookstore.exception;

import org.springframework.http.HttpStatus;
import static az.spring.bookstore.constant.ExceptionConstant.USER_NOT_FOUND;

public class UserNotFoundException extends GenericException {

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.value(), USER_NOT_FOUND);
    }

}
