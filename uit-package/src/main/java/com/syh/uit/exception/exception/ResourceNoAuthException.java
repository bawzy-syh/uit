package com.syh.uit.exception.exception;

import org.springframework.http.HttpStatus;

public class ResourceNoAuthException extends APIGeneralException {
    private static final HttpStatus status = HttpStatus.FORBIDDEN;
    private static final String error = "no auth";

    public ResourceNoAuthException(String description) {
        super(status, error, description);
    }
}
