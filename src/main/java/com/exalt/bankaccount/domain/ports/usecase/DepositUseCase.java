package com.exalt.bankaccount.domain.ports.usecase;

public interface DepositUseCase {
    void deposit(Long id, double amount);
}