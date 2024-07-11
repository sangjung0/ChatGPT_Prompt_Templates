package com.chatgpt_prompt_templates.exception;

import com.auth.Constants;
import com.auth.exception.ValidException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class CheckError {
    public static void checkValidException(BindingResult bindingResult) throws com.auth.exception.ValidException {
        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : bindingResult.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            throw new ValidException(Constants.VALID_ERROR,errors);
        }
    }
}
