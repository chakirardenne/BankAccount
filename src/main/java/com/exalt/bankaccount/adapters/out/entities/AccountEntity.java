package com.exalt.bankaccount.adapters.out.entities;

import com.exalt.bankaccount.domain.impl.AccountImpl;
import com.exalt.bankaccount.domain.intf.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double balance;
    private String name;
    @OneToMany(orphanRemoval=true, fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private List<TransactionEntity> transactions;

    public static Account toDomain(AccountEntity account) {
        return new AccountImpl(account.getId(), account.getBalance(), account.getName());
    }

    public static AccountEntity toEntity(Account account) {
        return AccountEntity.builder()
            .id(account.getId())
            .balance(account.getBalance())
            .name(account.getName())
            .transactions(account.getTransactionHistory().stream()
                .map(TransactionEntity::toEntity)
                .toList())
            .build();
    }
}
