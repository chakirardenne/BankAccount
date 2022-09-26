package com.exalt.bankaccount.application.dto;

import com.exalt.bankaccount.domain.model.intf.Transaction;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
public class TransationResponse {
    public String type;
    public double amount;
    public Date date;
    public double balance;

    public TransationResponse(String type, double amount, Date date, double balance) {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.balance = balance;
    }

    public static TransationResponse of(Transaction transaction) {
        return new TransationResponse(transaction.getTransactionType().getType(),
                transaction.getAmount(),
                transaction.getDate(),
                transaction.getBalance());
    }
}
