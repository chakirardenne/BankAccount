package com.exalt.bankaccount.configuration;

import com.exalt.bankaccount.application.exception.AccountNotFoundException;
import com.exalt.bankaccount.domain.exception.NegativeAmountInOperationException;
import com.exalt.bankaccount.domain.exception.NegativeBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {AccountNotFoundException.class})
    public ResponseEntity<Object> resourceNotFoundException(AccountNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NegativeBalanceException.class})
    public ResponseEntity<Object> negativeBalanceException(NegativeBalanceException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NegativeAmountInOperationException.class})
    public ResponseEntity<Object> negativeAmountInOperationException(NegativeAmountInOperationException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
    }
}
