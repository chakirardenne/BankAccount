package com.exalt.bankaccount.application.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(Long id) {
        super(String.format("Account not found : %s",id));
    }

}
