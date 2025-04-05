package com.example.demo.exception;

import lombok.Data;

@Data
public class ExceptionModel {
    private int status;
    private String errorCode;
    private String message;
}
