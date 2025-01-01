package com.template.spring_mongodb.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(ValidPassword password) { }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(password)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("비밀번호를 입력해주세요.").addConstraintViolation();
            return false;
        }

//        if (password.length() < 8) {
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate("Password must be at least 8 characters long").addConstraintViolation();
//            return false;
//        }
//
//        if (!Pattern.compile("[A-z]").matcher(password).find()) {
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate("Password must include at least one letter").addConstraintViolation();
//            return false;
//        }

        return true;
    }
}
