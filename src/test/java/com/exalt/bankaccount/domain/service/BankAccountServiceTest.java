package com.exalt.bankaccount.domain.service;

import com.exalt.bankaccount.adapters.in.api.AccountRestController;
import com.exalt.bankaccount.adapters.in.dto.CreateAccountRequest;
import com.exalt.bankaccount.adapters.in.dto.CreateAccountResponse;
import com.exalt.bankaccount.application.ports.in.CreateAccountUseCase;
import com.exalt.bankaccount.application.ports.in.DepositUseCase;
import com.exalt.bankaccount.application.ports.in.HistoryUseCase;
import com.exalt.bankaccount.application.ports.in.WithdrawUseCase;
import com.exalt.bankaccount.application.ports.out.AccountRepository;
import com.exalt.bankaccount.application.service.CreateAccountService;
import com.exalt.bankaccount.application.service.DepositService;
import com.exalt.bankaccount.application.service.HistoryService;
import com.exalt.bankaccount.application.service.WithdrawService;
import com.exalt.bankaccount.domain.exception.NegativeBalanceException;
import com.exalt.bankaccount.domain.impl.AccountImpl;
import com.exalt.bankaccount.domain.intf.Account;
import com.exalt.bankaccount.domain.intf.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    @MockBean
    private CreateAccountUseCase createService;
    @MockBean
    private ModelMapper modelMapper;
    static final double BALANCE_VALUE = 1000;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        modelMapper = mock(ModelMapper.class);
        historyService = new HistoryService(accountRepository);
        depositService = new DepositService(accountRepository);
        withdrawService = new WithdrawService(accountRepository);
        createService = new CreateAccountService(accountRepository, modelMapper);
    }

    @AfterEach
    void tearDown() {
        accountRepository = null;
        historyService = null;
    }

    @Test
    void shouldDepositOnAccountThenSaveIt() {
        final Account account = new AccountImpl(1L,BALANCE_VALUE, "accountName");
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        depositService.deposit(1L, 10);
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    void shouldWithDrawOnAccountThenSaveIt() throws NegativeBalanceException {
        final Account account = new AccountImpl(1L,BALANCE_VALUE, "accountName");
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        withdrawService.withdraw(1L, 10);
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    void shouldReturnHistoryForAccount() throws NegativeBalanceException {
        final Account account = new AccountImpl(1L,BALANCE_VALUE, "accountName");
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.getHistoryById(1L)).thenReturn(account.getTransactionHistory());
        historyService.getHistoryForAccount(1L);
        depositService.deposit(1L, 200);
        withdrawService.withdraw(1L, 59);
        List<Transaction> transactions = historyService.getHistoryForAccount(1L);
        assertEquals(2, transactions.size());
    }

    @Test
    void shouldCreateNewAccountAccount() {
        final Account account = new AccountImpl(1L,BALANCE_VALUE, "accountName");
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setId(account.getId());
        createAccountRequest.setBalance(account.getBalance());
        createAccountRequest.setName(account.getName());

        when(accountRepository.save(account)).thenReturn(account);
        createService.create(createAccountRequest);
        verify(accountRepository).save(any(Account.class));
    }
}