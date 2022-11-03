package com.exalt.bankaccount.domain.service;

import com.exalt.bankaccount.adapters.in.dto.CreateAccountRequest;
import com.exalt.bankaccount.application.converter.MapperTool;
import com.exalt.bankaccount.application.ports.out.AccountPort;
import com.exalt.bankaccount.application.service.CreateAccountService;
import com.exalt.bankaccount.application.service.DepositService;
import com.exalt.bankaccount.application.service.HistoryService;
import com.exalt.bankaccount.application.service.WithdrawService;
import com.exalt.bankaccount.domain.exception.NegativeBalanceException;
import com.exalt.bankaccount.domain.impl.AccountImpl;
import com.exalt.bankaccount.domain.intf.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceTest {
    @Mock
    AccountPort accountPort;
    @InjectMocks
    private HistoryService historyService;
    @InjectMocks
    private DepositService depositService;
    @InjectMocks
    private WithdrawService withdrawService;
    @InjectMocks
    private CreateAccountService createService;
    @Spy
    MapperTool mapperTool;
    static final double BALANCE_VALUE = 1000;

    @Test
    void shouldDepositOnAccountThenSaveIt() {
        final Account account = new AccountImpl(null, BALANCE_VALUE, "accountName", new ArrayList<>());
        when(accountPort.findById(account.getId())).thenReturn(Optional.of(account));
        depositService.deposit(account.getId(), 10);
        verify(accountPort).save(any(Account.class));
    }

    @Test
    void shouldWithDrawOnAccountThenSaveIt() throws NegativeBalanceException {
        final Account account = new AccountImpl(null, BALANCE_VALUE, "accountName", new ArrayList<>());
        when(accountPort.findById(account.getId())).thenReturn(Optional.of(account));
        withdrawService.withdraw(account.getId(), 10);
        verify(accountPort).save(any(Account.class));
    }

    @Test
    void shouldReturnHistoryForAccount() throws NegativeBalanceException {
        final Account account = new AccountImpl(null, BALANCE_VALUE, "accountName", new ArrayList<>());
        when(accountPort.findById(account.getId())).thenReturn(Optional.of(account));
        when(accountPort.getHistoryById(account.getId())).thenReturn(account.getTransactionHistory());
        depositService.deposit(account.getId(), 200);
        withdrawService.withdraw(account.getId(), 59);
        assertEquals(account.getTransactionHistory(), historyService.getHistoryForAccount(account.getId()));
    }

    @Test
    void shouldCreateNewAccountAccount() {
        final Account account = new AccountImpl(null, BALANCE_VALUE, "accountName", new ArrayList<>());
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setBalance(account.getBalance());
        createAccountRequest.setName(account.getName());
        when(accountPort.save(any())).thenReturn(account);
        createService.create(createAccountRequest);
        verify(accountPort).save(any(Account.class));
    }
}