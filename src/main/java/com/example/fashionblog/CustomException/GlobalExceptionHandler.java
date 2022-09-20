package com.example.fashionblog.CustomException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> NotFound(NotFoundException ex){
    return new ResponseEntity(new ErrorFormat(ex.getMessage(), HttpStatus.NOT_FOUND, LocalDate.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotAcceptableException.class)
    public ResponseEntity<Object> NotAcceptable(NotAcceptableException ex){
        return new ResponseEntity(new ErrorFormat(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE, LocalDate.now()), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> NotAcceptable(BadRequestException ex){
        return new ResponseEntity(new ErrorFormat(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDate.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> NotAcceptable(ForbiddenException ex){
        return new ResponseEntity(new ErrorFormat(ex.getMessage(), HttpStatus.FORBIDDEN, LocalDate.now()), HttpStatus.FORBIDDEN);
    }
}
