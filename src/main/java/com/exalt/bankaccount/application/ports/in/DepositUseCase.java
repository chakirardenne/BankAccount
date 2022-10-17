package com.exalt.bankaccount.application.ports.in;

public interface DepositUseCase {
    void deposit(Long id, double amount);
}