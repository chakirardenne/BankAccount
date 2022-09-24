package com.exalt.bankaccount.domain.model.intf;

import com.exalt.bankaccount.domain.model.exception.NegativeBalanceException;

import java.util.List;

public interface Account {
    void deposit(double amount);
    void withdraw(double amount) throws NegativeBalanceException;
    double getBalance();
    String getName();
    List<Transaction> getTransactionHitory();
}
