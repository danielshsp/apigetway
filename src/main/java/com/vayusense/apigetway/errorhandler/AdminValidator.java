package com.vayusense.apigetway.errorhandler;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class AdminValidator implements ConstraintValidator<Admin, String> {

    @Override
    public boolean isValid(String admin, ConstraintValidatorContext constraintValidatorContext) {
        return admin.equals("N") || admin.equals("Y") ;
    }
}
