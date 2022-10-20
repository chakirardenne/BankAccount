package com.exalt.bankaccount.adapters.in.dto;

import com.exalt.bankaccount.domain.intf.Account;
import com.exalt.bankaccount.domain.intf.Transaction;
import lombok.Data;

import java.util.List;

@Data
public class CreateAccountResponse {
    private final Long id;
    private final double balance;
    private final String name;
    private final List<Transaction> transactions;

    public static CreateAccountResponse of(Account account) {
        return new CreateAccountResponse(account.getId(), account.getBalance(), account.getName(), account.getTransactionHistory());
    }
}
