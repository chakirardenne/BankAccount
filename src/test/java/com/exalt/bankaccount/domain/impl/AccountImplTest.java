package com.exalt.bankaccount.domain.impl;

import com.exalt.bankaccount.domain.exception.NegativeBalanceException;
import com.exalt.bankaccount.domain.intf.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void withdraw(double amount) {
        account.withdraw(amount);
        assertEquals(BALANCE_VALUE - amount, account.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {1, 30, 600, 56, 765})
    void withdrawFromNegativeAccount(double amount) {
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
    void getTransactionHistory() {
        account.deposit(10);
        account.withdraw(5);
        List<Transaction> transactions = account.getTransactionHistory();
        assertEquals(transactions, account.getTransactionHistory());
    }

    @Test
    void getName() {
        assertEquals(ACCOUNT_NAME, account.getName());
    }
}