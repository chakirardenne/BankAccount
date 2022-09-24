package com.exalt.bankaccount.domain.impl;

import com.exalt.bankaccount.domain.intf.Transaction;

import java.util.Date;

public class TransactionImpl implements Transaction {
    private final TransactionType type;
    private final double amount;
    private final Date date;
    private final double balance;

    public TransactionImpl(TransactionType type, double amount, Date date, double balance) {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.balance = balance;
    }

    @Override
    public TransactionType getTransactionType() {
        return type;
    }

    @Override
    public String toString() {
        return "TransactionImpl{" +
                "type=" + type +
                ", amount=" + amount +
                ", date=" + date +
                ", balance=" + balance +
                '}';
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public double getBalance() {
        return balance;
    }
}
