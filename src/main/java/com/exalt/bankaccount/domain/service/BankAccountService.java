package com.exalt.bankaccount.domain.service;

import com.exalt.bankaccount.domain.model.exception.NegativeBalanceException;
import com.exalt.bankaccount.domain.model.intf.Account;
import com.exalt.bankaccount.domain.model.intf.Transaction;
import com.exalt.bankaccount.domain.ports.AccountRepository;
import com.exalt.bankaccount.domain.ports.usecase.DepositUseCase;
import com.exalt.bankaccount.domain.ports.usecase.HistoryUseCase;
import com.exalt.bankaccount.domain.ports.usecase.WithdrawUseCase;

import java.util.List;
import java.util.NoSuchElementException;

public class BankAccountService implements WithdrawUseCase, DepositUseCase, HistoryUseCase {
    private final AccountRepository accountRepository;

    public BankAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void deposit(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        account.deposit(amount);
        accountRepository.save(account);
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

    @Override
    public List<Transaction> getHistoryForAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        return account.getTransactionHitory();
    }
}
