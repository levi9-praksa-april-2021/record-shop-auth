package com.recordshop.auth.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator
        implements ConstraintValidator<Password, String> {

    @Override
    public void initialize(Password constraintAnnotation) {
    }
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context){
        return password.matches("^(?=.*[0-9])(?=.*[a-z]).{8,32}$");
    }
}
