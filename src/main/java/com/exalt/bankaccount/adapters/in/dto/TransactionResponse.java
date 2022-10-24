package com.exalt.bankaccount.adapters.in.dto;

import com.exalt.bankaccount.domain.intf.Transaction;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
@Data
@Builder
public class TransactionResponse {
    private String type;
    private double amount;
    private LocalDateTime date;
    private double balance;

    public static TransactionResponse of(Transaction transaction) {
        return TransactionResponse.builder()
                .type(transaction.getTransactionType().name())
                .amount(transaction.getAmount())
                .date(transaction.getDate())
                .balance(transaction.getBalance())
                .build();
    }
}
