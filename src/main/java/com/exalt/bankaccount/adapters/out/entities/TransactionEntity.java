package com.exalt.bankaccount.adapters.out.entities;

import com.exalt.bankaccount.domain.impl.TransactionImpl;
import com.exalt.bankaccount.domain.impl.TransactionType;
import com.exalt.bankaccount.domain.intf.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private double amount;
    private Date date;
    private double balance;

    public static Transaction toDomain(TransactionEntity transactionEntity) {
        return new TransactionImpl(transactionEntity.getId(),
                TransactionType.valueOf(transactionEntity.getType()),
                transactionEntity.getAmount(),
                transactionEntity.getDate(),
                transactionEntity.getBalance()
        );
    }

    public static TransactionEntity toEntity(Transaction transaction) {
        return TransactionEntity.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getTransactionType().getType())
                .date(transaction.getDate())
                .balance(transaction.getBalance())
                .build();
    }
}
