package com.exalt.bankaccount.domain.impl;

import com.exalt.bankaccount.domain.exception.IncorrectAmountValueInOperationException;
import com.exalt.bankaccount.domain.exception.NegativeBalanceException;
import com.exalt.bankaccount.domain.intf.Account;
import com.exalt.bankaccount.domain.intf.Transaction;

import java.util.List;

public class AccountImpl implements Account {
    private final Long id;
    private double balance;
    private final String name;
    private final List<Transaction> transactions;

    public AccountImpl(Long id, double balance, String name, List<Transaction> transactions) {
        this.id = id;
        this.balance = balance;
        this.name = name;
        this.transactions = transactions;
    }

    private void checkAmountValue(double amount) {
        if(amount <= 0)
            throw new IncorrectAmountValueInOperationException("Amount must be a strictly positive number");
    }

    @Override
    public void deposit(double amount) {
        checkAmountValue(amount);
        this.balance+=amount;
    }

    @Override
    public void withdraw(double amount) throws NegativeBalanceException {
        checkAmountValue(amount);
        if(balance < amount)
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountImpl account)) return false;

        if (Double.compare(account.balance, balance) != 0) return false;
        if (!id.equals(account.id)) return false;
        return name.equals(account.name);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }

}
