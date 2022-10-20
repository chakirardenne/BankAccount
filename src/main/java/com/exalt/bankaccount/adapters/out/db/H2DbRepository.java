package com.exalt.bankaccount.adapters.out.db;

import com.exalt.bankaccount.adapters.out.entities.AccountEntity;
import com.exalt.bankaccount.adapters.out.entities.TransactionEntity;
import com.exalt.bankaccount.application.ports.out.AccountRepository;
import com.exalt.bankaccount.domain.intf.Account;
import com.exalt.bankaccount.domain.intf.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class H2DbRepository implements AccountRepository {
    private final SpringDataJpaRepository repository;
    @Override
    public Optional<Account> findById(Long id) {
        AccountEntity account = repository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        return Optional.of(AccountEntity.toDomain(account));
    }
    @Override
    public Account save(Account account){
        return AccountEntity.toDomain(repository.save(AccountEntity.toEntity(account)));
    }

    @Override
    public List<Transaction> getHistory(Account account) {
        return repository.findById(account.getId())
                .orElseThrow(NoSuchElementException::new).getTransactions().stream()
                .map(TransactionEntity::toDomain)
                .toList();
    }
}
