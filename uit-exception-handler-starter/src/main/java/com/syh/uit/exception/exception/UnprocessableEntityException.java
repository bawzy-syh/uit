package com.syh.uit.exception.exception;

import org.springframework.http.HttpStatus;

public class UnprocessableEntityException extends APIGeneralException{
    private static final HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    private static final String error = "Unprocessable Entity";
    public UnprocessableEntityException(String description) {
        super(status, error, description);
    }
}
