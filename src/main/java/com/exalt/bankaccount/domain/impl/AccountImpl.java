package com.exalt.bankaccount.domain.impl;

import com.exalt.bankaccount.domain.exception.NegativeBalanceException;
import com.exalt.bankaccount.domain.intf.Account;
import com.exalt.bankaccount.domain.intf.Transaction;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
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

    public AccountImpl(Long id, double balance, String name, List<Transaction> transactions) {
        this.id = id;
        this.balance = balance;
        this.name = name;
        this.transactions = transactions;
    }

    private void checkAmountValue(double amount) {
        if(amount < 0)
            throw new IllegalArgumentException("Amount can't be negative");
    }

    @Override
    public void deposit(double amount) {
        checkAmountValue(amount);
        this.balance+=amount;
    }

    @Override
    public void withdraw(double amount) throws NegativeBalanceException {
        checkAmountValue(amount);
        if(balance <= amount)
            throw new NegativeBalanceException("Can't do this operation, balance can't be negative");
        this.balance-=amount;
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
    public List<Transaction> getTransactionHistory() {
        return transactions;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean addTransaction(Transaction transaction) {
        return transactions.add(transaction);
    }
}
