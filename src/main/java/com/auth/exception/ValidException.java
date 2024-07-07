package com.auth.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class ValidException extends Exception{
    private final Map<String,String> errors;
    public ValidException(String message, Map<String, String> errors){
        super(message);
        this.errors = errors;
    }
}
