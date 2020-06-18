package com.syh.uit.exception.exception;

import org.springframework.http.HttpStatus;

public class APIGeneralException extends Exception {
    private final HttpStatus status;
    private final String error;
    private final String description;

    public APIGeneralException(HttpStatus status, String error, String description){
        this.status = status;
        this.error = error;
        this.description = description;
    }
    public String getError() {
        return error;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return description;
    }
}
