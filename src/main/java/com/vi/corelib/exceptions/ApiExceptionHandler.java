package com.vi.corelib.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.MethodNotAllowedException;

import jakarta.persistence.EntityNotFoundException;
import javax.security.sasl.AuthenticationException;


@ControllerAdvice
public class ApiExceptionHandler {

   @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    public  ResponseError handleValidationError(EntityNotFoundException ex) {

        return new ResponseError("-2000", "DATA_NOT_FOUND",ex.getMessage());
    }
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(NullPointerException.class)
    public  ResponseError handleValidationError(NullPointerException ex) {
        
        return new ResponseError("-2001", "MISSING_VALUES",ex.getMessage());
    }
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    public  ResponseError handleValidationError(DataIntegrityViolationException ex) {
        
        return new ResponseError("-2002", "INTEGRITY_VIOLOATION",ex.getMessage());
    }
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(AuthenticationException.class)
    public  ResponseError handleValidationError(AuthenticationException ex) {
        
        return new ResponseError("-4000", "ACCESS DENIED/INVALID OR MISSING TOKEN",ex.getMessage());
    }
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(TokenException.class)
    public  ResponseError handleValidationError(TokenException ex) {
        
        return new ResponseError("-4001", "TOKEN_NOT_FOUND",ex.getMessage());
    }
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public  ResponseError handleValidationError(HttpMessageNotReadableException ex) {
        
        return new ResponseError("-4001", "Missing Body/Parameters/Path",ex.getMessage());
    }
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodNotAllowedException.class)
    public  ResponseError handleValidationError(MethodNotAllowedException ex) {
        
        return new ResponseError("-4001", "Missing Body/Parameters/Path",ex.getMessage());
    }
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public  ResponseError handleValidationError(Exception ex) {
         System.out.print(Exception.class);
        return new ResponseError("-4001", "Missing Body/Parameters/Path",ex.getMessage());
    }
}
