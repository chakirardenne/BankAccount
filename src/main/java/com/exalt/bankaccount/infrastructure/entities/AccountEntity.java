package com.exalt.bankaccount.infrastructure.entities;

import com.exalt.bankaccount.domain.model.impl.AccountImpl;
import com.exalt.bankaccount.domain.model.intf.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private double balance;
    private String name;
    @OneToMany
    private List<TransactionEntity> transactions;

    public AccountEntity(Long id, double balance, String name, List<TransactionEntity> transactions) {
        this.id = id;
        this.balance = balance;
        this.name = name;
        this.transactions = transactions;
    }

    public static Account toDomain(AccountEntity account) {
        return new AccountImpl(account.getId(), account.getBalance(), account.getName());
    }

    public static AccountEntity toEntity(Account account) {
        return new AccountEntity(account.getId(),
                account.getBalance(),
                account.getName(),
                account.getTransactionHitory().stream()
                        .map(TransactionEntity::toEntity)
                        .toList()
        );
    }
}
