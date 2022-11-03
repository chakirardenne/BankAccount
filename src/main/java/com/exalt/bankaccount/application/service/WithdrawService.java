package com.exalt.bankaccount.application.service;

import com.exalt.bankaccount.application.exception.AccountNotFoundException;
import com.exalt.bankaccount.application.ports.in.WithdrawUseCase;
import com.exalt.bankaccount.application.ports.out.AccountPort;
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
public class WithdrawService implements WithdrawUseCase {
    private final AccountPort accountPort;
    @Override
    @Transactional
    public void withdraw(Long id, double amount) {
        Account account = accountPort.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        account.withdraw(amount);
        account.addTransaction(new TransactionImpl(TransactionType.WITHDRAW,
                amount,
                LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC),
                account.getBalance()));
        accountPort.save(account);
    }
}
