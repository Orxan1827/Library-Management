package az.spring.bookstore.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.*;

import static az.spring.bookstore.constant.ValidationMessageConstants.EMAIL_REGEX;
import static az.spring.bookstore.constant.ValidationMessageConstants.EMAIL_REGEX_MESSAGE;

@Pattern(regexp = EMAIL_REGEX, message = EMAIL_REGEX_MESSAGE)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@NotBlank
@NotNull
@Constraint(validatedBy = {})
public @interface Email {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
