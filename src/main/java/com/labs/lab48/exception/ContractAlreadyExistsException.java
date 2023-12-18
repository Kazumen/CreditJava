package com.labs.lab48.exception;


public class ContractAlreadyExistsException extends Exception {
    public ContractAlreadyExistsException() {
        super();
    }
    public ContractAlreadyExistsException(String message) {
        super(message);
    }
    public ContractAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
