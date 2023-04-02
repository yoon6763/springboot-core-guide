package com.springboot.test.config.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TelephoneValidator implements ConstraintValidator<Telephone, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null) return false;
        return s.matches("01(?:0|[6-9])[.-]?(\\d{4})[.-]?(\\d{4})$");
    }
}
