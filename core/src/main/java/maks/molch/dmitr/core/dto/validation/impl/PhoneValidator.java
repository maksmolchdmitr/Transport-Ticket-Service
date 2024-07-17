package maks.molch.dmitr.core.dto.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import maks.molch.dmitr.core.dto.validation.Phone;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    private static final String PHONE_REGEX = "^\\+[1-9]\\d{1,14}$";

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        return phoneNumber != null && phoneNumber.matches(PHONE_REGEX);
    }
}
