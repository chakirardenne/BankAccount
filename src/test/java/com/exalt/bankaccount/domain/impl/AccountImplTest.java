package com.exalt.bankaccount.domain.impl;

import com.exalt.bankaccount.domain.model.exception.NegativeBalanceException;
import com.exalt.bankaccount.domain.model.impl.AccountImpl;
import com.exalt.bankaccount.domain.model.intf.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountImplTest {
    AccountImpl account;
    static final double BALANCE_VALUE = 1000;
    static final String ACCOUNT_NAME = "Name";
    static final Long ACCOUNT_ID = 1L;

    @BeforeEach
    void setUp() {
        account = new AccountImpl(ACCOUNT_ID, BALANCE_VALUE, ACCOUNT_NAME);
    }

    @ParameterizedTest
    @ValueSource(doubles = {10, 400, 500000, 3, 26})
    void deposit(double amount) {
        account.deposit(amount);
        assertEquals(BALANCE_VALUE + amount, account.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {1, 30, 600, 56, 765})
    void withdraw(double amount) throws NegativeBalanceException {
        account.withdraw(amount);
        assertEquals(BALANCE_VALUE - amount, account.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {1, 30, 600, 56, 765})
    void withdrawFromNegativeAccount(double amount) throws NegativeBalanceException {
        account.withdraw(BALANCE_VALUE);
        assertThrows(NegativeBalanceException.class, () -> account.withdraw(amount));
    }

    @Test
    void getBalance() {
        double accountBalance = account.getBalance();
        assertEquals(accountBalance, account.getBalance());
    }

    @AfterEach
    void tearDown(){
        account = null;
    }

    @Test
    void getTransactionHitory() throws NegativeBalanceException {
        account.deposit(10);
        account.withdraw(5);
        List<Transaction> transactions = account.getTransactionHitory();
        assertEquals(transactions, account.getTransactionHitory());
    }

    @Test
    void getName() {
        assertEquals(ACCOUNT_NAME, account.getName());
    }
}