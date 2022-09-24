package com.exalt.bankaccount.domain.model.exception;

public class NegativeBalanceException extends Exception {
    public NegativeBalanceException(String s) {
        super(s);
    }
}
