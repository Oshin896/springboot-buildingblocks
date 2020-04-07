package com.spring.boot.building.blocks.SpringBoot01.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class CustomeGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        CustomeErrorDetails customeErrorDetails=new CustomeErrorDetails(new Date(),"From Global Exception in handleMethodArgumentNotValid",ex.getMessage());
        return new ResponseEntity<Object>(customeErrorDetails,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomeErrorDetails customeErrorDetails=new CustomeErrorDetails(new Date(),"From Global Exception in handleHttpRequestMethodNotSupported-Method not allowed",ex.getMessage());
        return new ResponseEntity<Object>(customeErrorDetails,HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex,WebRequest webRequest){
        CustomeErrorDetails customeErrorDetails=new CustomeErrorDetails(new Date(),ex.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<Object>(customeErrorDetails,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request){
        CustomeErrorDetails customeErrorDetails=new CustomeErrorDetails(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<Object>(customeErrorDetails,HttpStatus.BAD_REQUEST);
    }
}
