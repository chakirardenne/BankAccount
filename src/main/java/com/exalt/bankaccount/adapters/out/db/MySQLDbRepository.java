package com.exalt.bankaccount.adapters.out.db;

import com.exalt.bankaccount.adapters.out.entities.AccountEntity;
import com.exalt.bankaccount.application.ports.out.AccountRepository;
import com.exalt.bankaccount.domain.intf.Account;

import java.util.NoSuchElementException;
import java.util.Optional;

public class MySQLDbRepository implements AccountRepository {
    private final SpringDataJpaRepository repository;
    public MySQLDbRepository(SpringDataJpaRepository repository) {
        this.repository = repository;
    }
    @Override
    public Optional<Account> findById(Long id) {
        AccountEntity account = repository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        return Optional.of(AccountEntity.toDomain(account));
    }

    @Override
    public void save(Account account){
        repository.save(AccountEntity.toEntity(account));
    }
}
