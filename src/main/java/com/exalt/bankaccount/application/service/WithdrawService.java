package com.exalt.bankaccount.application.service;

import com.exalt.bankaccount.application.ports.in.WithdrawUseCase;
import com.exalt.bankaccount.application.ports.out.AccountRepository;
import com.exalt.bankaccount.domain.intf.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        accountRepository.save(account);
    }
}
