package com.syh.uit.friend_apply_service.controller;

import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.friend_apply_service.model.response.APIErrorResponse;
import com.syh.uit.friend_apply_service.model.response.APIErrorResponse.ApiErrorResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //兜底
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public APIErrorResponse UnknownExceptionHandler(HttpServletRequest req, Exception e){
        logger.error("Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.toString());
        return new ApiErrorResponseBuilder()
                .withError("Unknown Error")
                .withError_description(e.toString())
                .build();
    }
    //请求内容不合规,内容不在body中
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public APIErrorResponse handleBindException(HttpServletRequest req, BindException e){
        logger.error("Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        return new ApiErrorResponseBuilder()
                .withError("Bad Request")
                .withError_description(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage())
                .build();
    }
    //请求内容不合规,内容在body中
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public APIErrorResponse handleValidException(HttpServletRequest req, MethodArgumentNotValidException e){
        logger.error("Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        return new ApiErrorResponseBuilder()
                .withError("Bad Request")
                .withError_description(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage())
                .build();
    }
    //请求转化出错
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageConversionException.class)
    public APIErrorResponse HttpMessageConversionException(HttpServletRequest req, HttpMessageConversionException e){
        logger.error("Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        return new ApiErrorResponseBuilder()
                .withError("Bad Request")
                .withError_description(e.toString())
                .build();
    }

    //访问错误的地址
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public APIErrorResponse NoHandlerFoundExceptionHandler(HttpServletRequest req, NoHandlerFoundException e){
        logger.error("Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        return new ApiErrorResponseBuilder()
                .withError("Service Not Found")
                .withError_description(e.getMessage())
                .build();
    }
    //访问不支持的方法
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public APIErrorResponse MethodNotSupportedExceptionHandler(HttpServletRequest req, HttpRequestMethodNotSupportedException e){
        logger.error("Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        return new ApiErrorResponseBuilder()
                .withError("Method Not Allowed")
                .withError_description(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = APIGeneralException.class)
    public ResponseEntity<Object> ApiGeneralExceptionHandler(HttpServletRequest req, APIGeneralException e){
        logger.error("Host {} invokes url {} ERROR: {} Trace: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage(),e.getStackTrace());
        APIErrorResponse response = new ApiErrorResponseBuilder()
                .withError(e.getError())
                .withError_description(e.getMessage())
                .build();
        return new ResponseEntity<>(response, e.getStatus());
    }
}
