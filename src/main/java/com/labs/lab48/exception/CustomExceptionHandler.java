package com.labs.lab48.exception;

import com.labs.lab48.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {
    @Autowired
    private EmailService emailService;


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException exception) throws MessagingException, UnsupportedEncodingException {
        return getErrorResponseResponseEntity(exception.getMessage(), exception);
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException exception) throws MessagingException, UnsupportedEncodingException {
        return getErrorResponseResponseEntity(exception.getMessage(), exception);
    }
    @ExceptionHandler(BankAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleBankAlreadyExistsException(BankAlreadyExistsException exception) throws MessagingException, UnsupportedEncodingException {
        return getErrorResponseResponseEntity(exception.getMessage(), exception);
    }
    @ExceptionHandler(LimitAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleLimitAlreadyExistsException(LimitAlreadyExistsException exception) throws MessagingException, UnsupportedEncodingException {
        return getErrorResponseResponseEntity(exception.getMessage(), exception);
    }
    @ExceptionHandler(SumOutOfLimitException.class)
    public ResponseEntity<ErrorResponse> handleSumOutOfLimitException(SumOutOfLimitException exception) throws MessagingException, UnsupportedEncodingException {
        return getErrorResponseResponseEntity(exception.getMessage(), exception);
    }
    @ExceptionHandler(ContractAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleContractAlreadyExistsException(ContractAlreadyExistsException exception) throws MessagingException, UnsupportedEncodingException {
        return getErrorResponseResponseEntity(exception.getMessage(), exception);
    }
    @ExceptionHandler(CreditAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCreditAlreadyExistsException(CreditAlreadyExistsException exception) throws MessagingException, UnsupportedEncodingException {
        return getErrorResponseResponseEntity(exception.getMessage(), exception);
    }

    private ResponseEntity<ErrorResponse> getErrorResponseResponseEntity(String message, Object exception) throws MessagingException, UnsupportedEncodingException {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Map<String, String> errors = new HashMap<>();
        errors.put("message", message);
        emailService.sendEmail("yurii.romanchak.oi.2023@lpnu.ua", message);
        log.error(message);
        return new ResponseEntity<>(new ErrorResponse(httpStatus,errors),httpStatus);
    }
}
