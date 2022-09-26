package com.exalt.bankaccount.domain.ports.usecase;

public interface WithdrawUseCase {
    void withdraw(Long id, double amount);
}
