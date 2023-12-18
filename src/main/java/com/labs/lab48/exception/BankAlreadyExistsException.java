package com.labs.lab48.exception;


public class BankAlreadyExistsException extends Exception {
    public BankAlreadyExistsException() {
        super();
    }
    public BankAlreadyExistsException(String message) {
        super(message);

    }
    public BankAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
