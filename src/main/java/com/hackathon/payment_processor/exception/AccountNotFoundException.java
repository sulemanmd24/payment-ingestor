package com.hackathon.payment_processor.exception;

public class AccountNotFoundException extends  RuntimeException{
    public AccountNotFoundException(
            String message) {

        super(message);
    }
}
