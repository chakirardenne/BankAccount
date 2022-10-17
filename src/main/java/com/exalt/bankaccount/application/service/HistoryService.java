package com.exalt.bankaccount.application.service;

import com.exalt.bankaccount.application.ports.in.HistoryUseCase;
import com.exalt.bankaccount.application.ports.out.AccountRepository;
import com.exalt.bankaccount.domain.intf.Account;
import com.exalt.bankaccount.domain.intf.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@Component(value = "HistoryUseCase")
public class HistoryService implements HistoryUseCase {
    private final AccountRepository accountRepository;

    public HistoryService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Override
    public List<Transaction> getHistoryForAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        return account.getTransactionHitory();
    }
}
