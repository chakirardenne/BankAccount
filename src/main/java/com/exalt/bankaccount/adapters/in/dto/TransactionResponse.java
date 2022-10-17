package com.exalt.bankaccount.adapters.in.dto;

import com.exalt.bankaccount.domain.intf.Transaction;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
public class TransactionResponse {
    public String type;
    public double amount;
    public Date date;
    public double balance;

    public TransactionResponse(String type, double amount, Date date, double balance) {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.balance = balance;
    }

    public static TransactionResponse of(Transaction transaction) {
        return new TransactionResponse(transaction.getTransactionType().getType(),
                transaction.getAmount(),
                transaction.getDate(),
                transaction.getBalance());
    }
}
