package com.hackathon.payment_processor.exception;

public class SuspendedAccountException extends RuntimeException {

    public SuspendedAccountException(
            String message) {

        super(message);
    }
}

