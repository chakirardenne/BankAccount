package com.exalt.bankaccount.application.service;

import com.exalt.bankaccount.application.ports.in.HistoryUseCase;
import com.exalt.bankaccount.application.ports.out.AccountRepository;
import com.exalt.bankaccount.domain.intf.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HistoryService implements HistoryUseCase {
    private final AccountRepository accountRepository;
    @Override
    @Transactional
    public List<Transaction> getHistoryForAccount(Long id) {
        return accountRepository.getHistoryById(id);
    }
}
