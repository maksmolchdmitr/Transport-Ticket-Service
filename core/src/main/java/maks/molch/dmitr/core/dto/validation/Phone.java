package maks.molch.dmitr.core.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import maks.molch.dmitr.core.dto.validation.impl.PhoneValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
@Documented
public @interface Phone {
    String message() default "Incorrect phone number!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
