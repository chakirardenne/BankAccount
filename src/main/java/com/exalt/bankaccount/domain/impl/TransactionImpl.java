package com.exalt.bankaccount.domain.impl;

import com.exalt.bankaccount.domain.intf.Transaction;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionImpl implements Transaction {
    private final TransactionType type;
    private final double amount;
    private final LocalDateTime date;
    private final double balance;

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
