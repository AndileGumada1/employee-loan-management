package com.atlas.loan.application.exceptions;

public class TransactionsNotFoundException extends RuntimeException{
    public TransactionsNotFoundException(String message) {
        super(message);
    }
}
