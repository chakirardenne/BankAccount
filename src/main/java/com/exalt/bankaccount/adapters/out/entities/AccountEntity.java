package com.exalt.bankaccount.adapters.out.entities;

import com.exalt.bankaccount.domain.impl.AccountImpl;
import com.exalt.bankaccount.domain.intf.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
        return new AccountImpl(account.getId(),
                account.getBalance(),
                account.getName(),
                account.getTransactions().stream().map(TransactionEntity::toDomain).collect(Collectors.toList()));
    }
}
