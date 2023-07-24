package com.miguel.prueba.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(String message, Object... args) {
        super(String.format(message, args));
    }

}
