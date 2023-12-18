package com.labs.lab48.exception;

public class SumOutOfLimitException extends Exception {
    public SumOutOfLimitException() {
        super();
    }
    public SumOutOfLimitException(String message) {
        super(message);
    }
    public SumOutOfLimitException(String message, Throwable cause) {
        super(message, cause);
    }
}
