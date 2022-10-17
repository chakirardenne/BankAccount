package com.exalt.bankaccount.domain.exception;

public class NegativeBalanceException extends Exception {
    public NegativeBalanceException(String s) {
        super(s);
    }
}
