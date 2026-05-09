package com.hackathon.payment_processor.exception;


public class InvalidCurrencyException
        extends RuntimeException {

    public InvalidCurrencyException(
            String message) {

        super(message);
    }
}
