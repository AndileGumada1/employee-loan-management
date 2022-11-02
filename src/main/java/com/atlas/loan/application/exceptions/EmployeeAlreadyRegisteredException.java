package com.atlas.loan.application.exceptions;

public class EmployeeAlreadyRegisteredException extends RuntimeException {
    public EmployeeAlreadyRegisteredException(String msg) {
        super(msg);
    }
}
