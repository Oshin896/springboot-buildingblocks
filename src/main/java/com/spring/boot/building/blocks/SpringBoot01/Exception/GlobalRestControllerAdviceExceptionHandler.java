package com.spring.boot.building.blocks.SpringBoot01.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

//@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomeErrorDetails handleUserNameNotFound(UserNotFoundException ex){
        return new CustomeErrorDetails(new Date(),"In Rest Controller Advice",ex.getMessage());
    }
}
