package com.syh.uit.exception.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends APIGeneralException {
    private static final HttpStatus status = HttpStatus.BAD_REQUEST;
    private static final String error = "BadRequest";

    public BadRequestException(String description) {
        super(status, error, description);
    }
}
