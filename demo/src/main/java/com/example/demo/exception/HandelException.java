package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandelException {
    @ExceptionHandler({HrmException.class})
    public ResponseEntity<ExceptionModel> handelException(HrmException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getExceptionModel());
    }
}
