package com.exalt.bankaccount.application.ports.in;

import com.exalt.bankaccount.domain.exception.NegativeBalanceException;

public interface WithdrawUseCase {
    void withdraw(Long id, double amount) throws NegativeBalanceException;
}
