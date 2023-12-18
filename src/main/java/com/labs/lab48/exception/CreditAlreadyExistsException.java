package com.labs.lab48.exception;


public class CreditAlreadyExistsException extends Exception {
    public CreditAlreadyExistsException() {
        super();
    }
    public CreditAlreadyExistsException(String message) {
                super(message);
            }
    public CreditAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
