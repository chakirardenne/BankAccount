package com.exalt.bankaccount.application.service;

import com.exalt.bankaccount.application.ports.in.HistoryUseCase;
import com.exalt.bankaccount.application.ports.out.AccountRepository;
import com.exalt.bankaccount.domain.intf.Account;
import com.exalt.bankaccount.domain.intf.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Component(value = "HistoryUseCase")
@RequiredArgsConstructor
public class HistoryService implements HistoryUseCase {
    private final AccountRepository accountRepository;
    @Override
    @Transactional
    public List<Transaction> getHistoryForAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        return account.getTransactionHistory();
    }
}
