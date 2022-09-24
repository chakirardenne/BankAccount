package com.exalt.bankaccount.infrastructure.entities;

import com.exalt.bankaccount.domain.model.impl.TransactionImpl;
import com.exalt.bankaccount.domain.model.impl.TransactionType;
import com.exalt.bankaccount.domain.model.intf.Transaction;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String type;
    private double amount;
    private Date date;
    private double balance;

    public TransactionEntity(Long id, String type, double amount, Date date, double balance) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.balance = balance;
    }

    public Transaction toDomain(TransactionEntity transactionEntity) {
        return new TransactionImpl(id, TransactionType.valueOf(transactionEntity.getType()),
                transactionEntity.getAmount(),
                transactionEntity.getDate(),
                transactionEntity.getBalance()
        );
    }

    public static TransactionEntity toEntity(Transaction transaction) {
        return new TransactionEntity(transaction.getId(), transaction.getTransactionType().getType(),
                transaction.getAmount(),
                transaction.getDate(),
                transaction.getBalance()
        );
    }

}
