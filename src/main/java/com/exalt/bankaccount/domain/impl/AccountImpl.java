package com.exalt.bankaccount.domain.impl;

import com.exalt.bankaccount.domain.exception.NegativeBalanceException;
import com.exalt.bankaccount.domain.intf.Account;
import com.exalt.bankaccount.domain.intf.Transaction;

import java.util.ArrayList;
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

    private void checkBalanceState(double amount) {
        if(Double.sum(amount, balance) <= 0 )
            throw new NegativeBalanceException("Can't withdraw from account, balance is negative");
    }

    @Override
    public void deposit(double amount) {
        checkBalanceState(amount);
        this.balance+=amount;
    }

    @Override
    public void withdraw(double amount) throws NegativeBalanceException {
        checkBalanceState(amount);
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
