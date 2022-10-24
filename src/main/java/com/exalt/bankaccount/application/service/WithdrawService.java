package com.exalt.bankaccount.application.service;

import com.exalt.bankaccount.application.ports.in.WithdrawUseCase;
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
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class WithdrawService implements WithdrawUseCase {
    private final AccountRepository accountRepository;
    @Override
    @Transactional
    public void withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        account.withdraw(amount);
        account.addTransaction(new TransactionImpl(TransactionType.DEPOSIT,
                amount,
                LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC),
                account.getBalance()));
        accountRepository.save(account);
    }
}
