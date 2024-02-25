package by.itacademy.environment.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static by.itacademy.environment.util.Constants.OBJECT_NOT_VALID_MESSAGE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = AtLeastOneFieldPresentValidator.class)
public @interface AtLeastOneFieldPresent {
    String message() default OBJECT_NOT_VALID_MESSAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
