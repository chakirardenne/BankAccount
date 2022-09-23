package com.exalt.bankaccount.domain.impl;

import com.exalt.bankaccount.domain.intf.Account;

public class AccountImpl implements Account {
    private double balance;

    public AccountImpl(double balance) {
        this.balance = balance;
    }

    @Override
    public void deposit(double amount) {
        this.balance+=amount;
    }

    @Override
    public void withdraw(double amount) {
        this.balance-=amount;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }
}
