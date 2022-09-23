package com.exalt.bankaccount.domain.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class AccountImplTest {
    AccountImpl account;
    static final double BALANCE_VALUE = 10;

    @BeforeEach
    void setUp() {
        account = new AccountImpl(BALANCE_VALUE);
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

    @Test
    void getBalance() {
        double accountBalance = account.getBalance();
        assertEquals(accountBalance, account.getBalance());
    }

    @AfterEach
    void tearDown(){
        account = null;
    }
}