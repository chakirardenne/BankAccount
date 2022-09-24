package com.exalt.bankaccount.infrastructure.adapters;

import com.exalt.bankaccount.domain.model.intf.Account;
import com.exalt.bankaccount.domain.ports.AccountRepository;
import com.exalt.bankaccount.infrastructure.entities.AccountEntity;

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
