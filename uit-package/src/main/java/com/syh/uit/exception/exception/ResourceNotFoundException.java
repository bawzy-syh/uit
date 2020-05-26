package com.syh.uit.exception.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends APIGeneralException {
    private static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    private static final String error = "ResourceNotFound";

    public ResourceNotFoundException(String description){
        super(httpStatus,error,description);
    }
}
