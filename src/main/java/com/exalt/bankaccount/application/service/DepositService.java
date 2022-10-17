package com.exalt.bankaccount.application.service;

import com.exalt.bankaccount.application.ports.in.DepositUseCase;
import com.exalt.bankaccount.application.ports.out.AccountRepository;
import com.exalt.bankaccount.domain.intf.Account;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Transactional
@Component(value = "DepositUseCase")
public class DepositService implements DepositUseCase {
    private final AccountRepository accountRepository;

    public DepositService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Override
    public void deposit(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        account.deposit(amount);
        accountRepository.save(account);
    }
}
