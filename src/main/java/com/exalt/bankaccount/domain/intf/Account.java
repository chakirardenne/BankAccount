package com.exalt.bankaccount.domain.intf;

import com.exalt.bankaccount.domain.exception.NegativeBalanceException;

import java.util.List;

public interface Account {
    Long getId();
    void deposit(double amount);
    void withdraw(double amount) throws NegativeBalanceException;
    double getBalance();
    String getName();
    List<Transaction> getTransactionHistory();
    boolean addTransaction(Transaction transaction);

}
