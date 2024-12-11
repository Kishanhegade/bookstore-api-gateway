package com.bridgelabz.bsa.apigateway.exception;

public class MissingAuthorizationHeaderException extends RuntimeException {

    private String message;

    public MissingAuthorizationHeaderException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
