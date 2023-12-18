package com.labs.lab48.exception;


public class LimitAlreadyExistsException extends Exception {
    public LimitAlreadyExistsException() {
        super();
    }
    public LimitAlreadyExistsException(String message) {
        super(message);
    }
    public LimitAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
