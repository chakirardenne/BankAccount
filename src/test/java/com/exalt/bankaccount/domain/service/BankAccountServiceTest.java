package com.exalt.bankaccount.domain.service;

import com.exalt.bankaccount.domain.model.impl.AccountImpl;
import com.exalt.bankaccount.domain.model.intf.Account;
import com.exalt.bankaccount.domain.model.intf.Transaction;
import com.exalt.bankaccount.domain.ports.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BankAccountServiceTest {
    private AccountRepository accountRepository;
    private BankAccountService bankAccountService;
    static final double BALANCE_VALUE = 1000;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        bankAccountService = new BankAccountService(accountRepository);
    }

    @AfterEach
    void tearDown() {
        accountRepository = null;
        bankAccountService = null;
    }

    @Test
    void shouldDepositOnAccountThenSaveIt() {
        final Account account = new AccountImpl(1L, BALANCE_VALUE, "accountName");
        bankAccountService.deposit(account.getId(), 10);
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    void shouldWithDrawOnAccountThenSaveIt() {
        final Account account = new AccountImpl(1L, BALANCE_VALUE, "accountName");
        bankAccountService.withdraw(account.getId(), 10);
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    void shouldReturnHistoryForAccount() {
        final Account account = new AccountImpl(1L, BALANCE_VALUE, "accountName");
        bankAccountService.getHistoryForAccount(account.getId());
        bankAccountService.deposit(account.getId(), 200);
        bankAccountService.withdraw(account.getId(), 59);
        List<Transaction> transactions = bankAccountService.getHistoryForAccount(account.getId());
        assertEquals(2, transactions.size());
    }
}