package com.exalt.bankaccount.adapters.out.db;

import com.exalt.bankaccount.adapters.out.entities.AccountEntity;
import com.exalt.bankaccount.adapters.out.entities.TransactionEntity;
import com.exalt.bankaccount.application.ports.out.AccountPort;
import com.exalt.bankaccount.domain.impl.AccountImpl;
import com.exalt.bankaccount.domain.intf.Account;
import com.exalt.bankaccount.domain.intf.Transaction;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class H2DbAdapter implements AccountPort {
    private final SpringDataJpaRepository repository;
    private final ModelMapper modelMapper;
    @Override
    public Optional<Account> findById(Long id) {
        return repository.findById(id).map(AccountEntity::toDomain);
    }
    @Override
    public Account save(Account account){
        AccountEntity accountEntity = modelMapper.map(account, AccountEntity.class);
        List<TransactionEntity> transactionEntities = modelMapper.map(account.getTransactionHistory(), new TypeToken<List<TransactionEntity>>() {}.getType());
        accountEntity.setTransactions(transactionEntities);
        accountEntity  = repository.save(accountEntity);
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
