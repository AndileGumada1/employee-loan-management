package com.atlas.loan.application.exceptions;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(String message) {
        super(message);
    }
}
