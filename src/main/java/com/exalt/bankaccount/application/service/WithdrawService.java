package com.exalt.bankaccount.application.service;

import com.exalt.bankaccount.application.ports.in.WithdrawUseCase;
import com.exalt.bankaccount.application.ports.out.AccountRepository;
import com.exalt.bankaccount.domain.exception.NegativeBalanceException;
import com.exalt.bankaccount.domain.intf.Account;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Transactional
@Component(value = "WithdrawUseCase")
public class WithdrawService implements WithdrawUseCase {
    private final AccountRepository accountRepository;

    public WithdrawService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Override
    public void withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        try {
            account.withdraw(amount);
        } catch (NegativeBalanceException e) {
            throw new RuntimeException(e);
        }
        accountRepository.save(account);
    }
}
