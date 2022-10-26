package com.exalt.bankaccount.application.service;

import com.exalt.bankaccount.application.exception.AccountNotFoundException;
import com.exalt.bankaccount.application.ports.in.DepositUseCase;
import com.exalt.bankaccount.application.ports.out.AccountRepository;
import com.exalt.bankaccount.domain.impl.TransactionImpl;
import com.exalt.bankaccount.domain.impl.TransactionType;
import com.exalt.bankaccount.domain.intf.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class DepositService implements DepositUseCase {
    private final AccountRepository accountRepository;
    @Override
    @Transactional
    public void deposit(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        account.deposit(amount);
        account.addTransaction(new TransactionImpl(TransactionType.DEPOSIT,
                amount,
                LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC),
                account.getBalance()));
        accountRepository.save(account);
    }
}
