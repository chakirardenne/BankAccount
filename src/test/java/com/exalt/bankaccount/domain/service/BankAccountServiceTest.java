package com.exalt.bankaccount.domain.service;

import com.exalt.bankaccount.adapters.in.api.AccountRestController;
import com.exalt.bankaccount.application.ports.in.DepositUseCase;
import com.exalt.bankaccount.application.ports.in.HistoryUseCase;
import com.exalt.bankaccount.application.ports.in.WithdrawUseCase;
import com.exalt.bankaccount.application.ports.out.AccountRepository;
import com.exalt.bankaccount.application.service.DepositService;
import com.exalt.bankaccount.application.service.HistoryService;
import com.exalt.bankaccount.application.service.WithdrawService;
import com.exalt.bankaccount.domain.impl.AccountImpl;
import com.exalt.bankaccount.domain.intf.Account;
import com.exalt.bankaccount.domain.intf.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
@WebMvcTest(controllers = AccountRestController.class)
class BankAccountServiceTest {
    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private HistoryUseCase historyService;
    @MockBean
    private DepositUseCase depositService;
    @MockBean
    private WithdrawUseCase withdrawService;
    static final double BALANCE_VALUE = 1000;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        historyService = new HistoryService(accountRepository);
        depositService = new DepositService(accountRepository);
        withdrawService = new WithdrawService(accountRepository);
    }

    @AfterEach
    void tearDown() {
        accountRepository = null;
        historyService = null;
    }

    @Test
    void shouldDepositOnAccountThenSaveIt() {
        final Account account = new AccountImpl(1L, BALANCE_VALUE, "accountName");
        depositService.deposit(account.getId(), 10);
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    void shouldWithDrawOnAccountThenSaveIt() {
        final Account account = new AccountImpl(1L, BALANCE_VALUE, "accountName");
        withdrawService.withdraw(account.getId(), 10);
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    void shouldReturnHistoryForAccount() {
        final Account account = new AccountImpl(1L, BALANCE_VALUE, "accountName");
        accountRepository.save(account);
        historyService.getHistoryForAccount(account.getId());
        depositService.deposit(account.getId(), 200);
        withdrawService.withdraw(account.getId(), 59);
        List<Transaction> transactions = historyService.getHistoryForAccount(account.getId());
        assertEquals(2, transactions.size());
    }
}