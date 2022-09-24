package com.exalt.bankaccount.domain.model.impl;

import com.exalt.bankaccount.domain.model.exception.NegativeBalanceException;
import com.exalt.bankaccount.domain.model.intf.Account;
import com.exalt.bankaccount.domain.model.intf.Transaction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountImpl implements Account {
    private final Long id;
    private double balance;
    private final String name;
    private final List<Transaction> transactions;

    public AccountImpl(Long id, double balance, String name) {
        this.id = id;
        this.balance = balance;
        this.name = name;
        this.transactions = new ArrayList<>();
    }

    @Override
    public void deposit(double amount) {
        this.balance+=amount;
        this.transactions.add(new TransactionImpl(TransactionType.DEPOSIT, amount, Date.from(Instant.now()), this.getBalance()));
    }

    @Override
    public void withdraw(double amount) throws NegativeBalanceException {
        if(this.balance <= 0)
            throw new NegativeBalanceException("Can't withdraw from account, balance is negative");
        this.balance-=amount;
        this.transactions.add(new TransactionImpl(TransactionType.WITHDRAW, amount, Date.from(Instant.now()), this.getBalance()));
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Transaction> getTransactionHitory() {
        return transactions;
    }
}
