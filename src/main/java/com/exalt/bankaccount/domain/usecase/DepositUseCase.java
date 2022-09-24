package com.exalt.bankaccount.domain.usecase;

public interface DepositUseCase {
    void deposit(Long id, double amount);
}