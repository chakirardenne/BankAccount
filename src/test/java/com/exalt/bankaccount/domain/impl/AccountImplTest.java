package com.exalt.bankaccount.domain.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountImplTest {
    AccountImpl account;
    static final double BALANCE_VALUE = 10;
    static final double AMOUNT_TO_DEPOSIT = 30;
    static final double AMOUNT_TO_WITHDRAW = 15;

    @BeforeEach
    void setUp() {
        account = new AccountImpl(BALANCE_VALUE);
    }

    @Test
    void deposit() {
        account.deposit(AMOUNT_TO_DEPOSIT);
        assertEquals(BALANCE_VALUE + AMOUNT_TO_DEPOSIT, account.getBalance());
    }

    @Test
    void withdraw() {
        account.withdraw(AMOUNT_TO_WITHDRAW);
        assertEquals(BALANCE_VALUE - AMOUNT_TO_WITHDRAW, account.getBalance());
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