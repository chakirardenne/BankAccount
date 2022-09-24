package com.exalt.bankaccount.domain.impl;

import com.exalt.bankaccount.domain.exception.NegativeBalanceException;
import com.exalt.bankaccount.domain.intf.Account;
import com.exalt.bankaccount.domain.intf.Transaction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountImpl implements Account {
    private double balance;
    private final List<Transaction> transactions;

    public AccountImpl(double balance) {
        this.balance = balance;
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
        return this.balance;
    }

    @Override
    public List<Transaction> getTransactionHitory() {
        return this.transactions;
    }
}
