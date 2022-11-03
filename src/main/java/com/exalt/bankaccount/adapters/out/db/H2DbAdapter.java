package com.exalt.bankaccount.adapters.out.db;

import com.exalt.bankaccount.adapters.out.entities.AccountEntity;
import com.exalt.bankaccount.application.converter.MapperTool;
import com.exalt.bankaccount.application.ports.out.AccountPort;
import com.exalt.bankaccount.domain.intf.Account;
import com.exalt.bankaccount.domain.intf.Transaction;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class H2DbAdapter implements AccountPort {
    private final SpringDataJpaRepository repository;
    private final MapperTool mapperTool;
    @Override
    public Optional<Account> findById(Long id) {
        return repository.findById(id).map(mapperTool::accountEntityToAccount);
    }
    @Override
    public Account save(Account account){
        AccountEntity accountEntity = mapperTool.accountToAccountEntity(account);
        accountEntity  = repository.save(accountEntity);
        return mapperTool.accountEntityToAccount(accountEntity);
    }

    @Override
    public List<Transaction> getHistoryById(Long id) {
        return repository.findById(id).map(AccountEntity::getTransactions).stream()
                .flatMap(List::stream)
                .map(mapperTool::transactionEntityToTransaction)
                .collect(Collectors.toList());
    }
}
