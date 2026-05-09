package com.hackathon.payment_processor.exception;

public class DuplicatePaymentException
        extends RuntimeException {

    public DuplicatePaymentException(
            String message) {

        super(message);
    }
}