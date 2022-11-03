package com.exalt.bankaccount.domain.intf;

import com.exalt.bankaccount.domain.impl.TransactionType;

import java.time.LocalDateTime;

public interface Transaction {
    TransactionType getType();
    double getAmount();
    LocalDateTime getDate();
    double getBalance();
}
