package com.auth.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.HashMap;
import java.util.Map;

import com.auth.Constants;

public class CheckError {
    public static void checkValidException(BindingResult bindingResult) throws ValidException {
        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : bindingResult.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            throw new ValidException(Constants.VALID_ERROR,errors);
        }
    }
}
