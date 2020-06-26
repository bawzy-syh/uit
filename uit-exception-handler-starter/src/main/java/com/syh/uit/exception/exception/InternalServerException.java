package com.syh.uit.exception.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends APIGeneralException{
    private static final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final String error = "INTERNAL_SERVER_ERROR";

    public InternalServerException(String description) {
        super(status, error, description);
    }
}
