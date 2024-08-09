package com.example.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException ex)
    {
        String message=ex.getExceptionMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
