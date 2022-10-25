package com.exalt.bankaccount.adapters.out.db;

import com.exalt.bankaccount.adapters.out.entities.AccountEntity;
import com.exalt.bankaccount.adapters.out.entities.TransactionEntity;
import com.exalt.bankaccount.application.ports.out.AccountRepository;
import com.exalt.bankaccount.domain.impl.AccountImpl;
import com.exalt.bankaccount.domain.intf.Account;
import com.exalt.bankaccount.domain.intf.Transaction;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class H2DbRepository implements AccountRepository {
    private final SpringDataJpaRepository repository;
    private final ModelMapper modelMapper;
    @Override
    public Optional<Account> findById(Long id) {
        return repository.findById(id).map(AccountEntity::toDomain);
    }
    @Override
    public Account save(Account account){

        AccountEntity accountEntity = repository.save(modelMapper.map(account, AccountEntity.class));
        return new AccountImpl(accountEntity.getId(),
                accountEntity.getBalance(),
                accountEntity.getName(),
                accountEntity.getTransactions().stream()
                        .map(TransactionEntity::toDomain)
                        .toList()
                );
    }

    @Override
    public List<Transaction> getHistoryById(Long id) {
        return repository.findById(id).map(AccountEntity::getTransactions).stream()
                .flatMap(List::stream)
                .map(TransactionEntity::toDomain)
                .toList();
    }
}
