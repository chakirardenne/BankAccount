package com.exalt.bankaccount.domain.usecase;

import com.exalt.bankaccount.domain.model.intf.Transaction;

import java.util.List;

public interface HistoryUseCase {
    List<Transaction> getHistoryForAccount(Long id);
}
