package com.imooc.houjiangtao.alllearning.util;

import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidatorUtils {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    public static <T> void validate(T object,Class<?> ... groups){
        Set<ConstraintViolation<T>> validate = validator.validate(object, groups);
        if(!CollectionUtils.isEmpty(validate)){
            StringBuilder exceptionMessage = new StringBuilder();
            validate.forEach(exceptionMessage::append);
            throw new RuntimeException(exceptionMessage.toString());
        }
    }
}
