package com.exalt.bankaccount.domain.intf;

import com.exalt.bankaccount.domain.impl.TransactionType;

import java.util.Date;

public interface Transaction {
    TransactionType getTransactionType();
    double getAmount();
    Date getDate();
    double getBalance();
}
