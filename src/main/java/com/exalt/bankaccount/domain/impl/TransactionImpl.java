package com.exalt.bankaccount.domain.impl;

import com.exalt.bankaccount.domain.intf.Transaction;

import java.time.LocalDateTime;

public class TransactionImpl implements Transaction {
    private final TransactionType type;
    private final double amount;
    private final LocalDateTime date;
    private final double balance;

    public TransactionImpl(TransactionType type, double amount, LocalDateTime date, double balance) {
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
    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public double getBalance() {
        return balance;
    }

}
