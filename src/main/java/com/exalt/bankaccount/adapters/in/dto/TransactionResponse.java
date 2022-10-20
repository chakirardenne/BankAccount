package com.exalt.bankaccount.adapters.in.dto;

import com.exalt.bankaccount.domain.intf.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@Data
@AllArgsConstructor
public class TransactionResponse {
    private String type;
    private double amount;
    private Date date;
    private double balance;

    public static TransactionResponse of(Transaction transaction) {
        return new TransactionResponse(transaction.getTransactionType().getType(),
                transaction.getAmount(),
                transaction.getDate(),
                transaction.getBalance());
    }
}
