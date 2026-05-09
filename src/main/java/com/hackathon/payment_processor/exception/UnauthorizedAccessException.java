package com.hackathon.payment_processor.exception;

public class UnauthorizedAccessException
        extends RuntimeException {

    public UnauthorizedAccessException(
            String message) {

        super(message);
    }
}