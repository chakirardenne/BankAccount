package com.exalt.bankaccount.application.ports.in;

import com.exalt.bankaccount.domain.intf.Transaction;

import java.util.List;

public interface HistoryUseCase {
    List<Transaction> getHistoryForAccount(Long id);
}
