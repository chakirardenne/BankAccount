package com.exalt.bankaccount.domain.impl;

public enum TransactionType {
    DEPOSIT("Deposit"),WITHDRAW("Withdraw");
    private final String type;
    TransactionType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
