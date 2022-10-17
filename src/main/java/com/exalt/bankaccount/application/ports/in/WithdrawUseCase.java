package com.exalt.bankaccount.application.ports.in;

public interface WithdrawUseCase {
    void withdraw(Long id, double amount);
}
