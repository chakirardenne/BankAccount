package com.exalt.bankaccount.domain.exception;

public class NegativeBalanceException extends RuntimeException {
    public NegativeBalanceException(String s) {
        super(s);
    }
}
