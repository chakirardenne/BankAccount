package com.exalt.bankaccount.adapters.out.entities;

import com.exalt.bankaccount.domain.impl.AccountImpl;
import com.exalt.bankaccount.domain.intf.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private double balance;
    private String name;
    @OneToMany
    private List<TransactionEntity> transactions;

    public static Account toDomain(AccountEntity account) {
        return new AccountImpl(account.getId(), account.getBalance(), account.getName());
    }

    public static AccountEntity toEntity(Account account) {
        return new AccountEntity(account.getId(),
                account.getBalance(),
                account.getName(),
                account.getTransactionHistory().stream()
                        .map(TransactionEntity::toEntity)
                        .toList()
        );
    }
}
