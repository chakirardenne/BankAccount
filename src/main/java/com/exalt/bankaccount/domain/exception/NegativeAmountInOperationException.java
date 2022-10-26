package com.exalt.bankaccount.domain.exception;

public class NegativeAmountInOperationException extends RuntimeException{
    public NegativeAmountInOperationException() {
        super("Amount can't be negative");
    }

}
