package com.exalt.bankaccount.application.service;

import com.exalt.bankaccount.application.exception.AccountNotFoundException;
import com.exalt.bankaccount.application.ports.in.HistoryUseCase;
import com.exalt.bankaccount.application.ports.out.AccountPort;
import com.exalt.bankaccount.domain.intf.Account;
import com.exalt.bankaccount.domain.intf.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HistoryService implements HistoryUseCase {
    private final AccountPort accountPort;
    @Override
    @Transactional
    public List<Transaction> getHistoryForAccount(Long id) {
        Account account = accountPort.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        return accountPort.getHistoryById(account.getId());
    }
}
