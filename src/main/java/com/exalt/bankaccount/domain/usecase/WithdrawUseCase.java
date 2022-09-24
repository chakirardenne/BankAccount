package com.exalt.bankaccount.domain.usecase;

public interface WithdrawUseCase {
    void withdraw(Long id, double amount);
}
