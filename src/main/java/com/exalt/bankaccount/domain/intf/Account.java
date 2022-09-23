package com.exalt.bankaccount.domain.intf;

public interface Account {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
}
