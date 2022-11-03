package com.exalt.bankaccount.adapters.out.entities;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double balance;
    private String name;
    @OneToMany(orphanRemoval=true, fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private List<TransactionEntity> transactions;
}
