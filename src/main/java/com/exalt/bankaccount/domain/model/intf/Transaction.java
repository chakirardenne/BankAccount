package com.exalt.bankaccount.domain.model.intf;

import com.exalt.bankaccount.domain.model.impl.TransactionType;

import java.util.Date;

public interface Transaction {
    TransactionType getTransactionType();
    double getAmount();
    Date getDate();
    double getBalance();
    Long getId();
}
