package com.exalt.bankaccount.adapters.in.dto;

import com.exalt.bankaccount.domain.intf.Account;
import com.exalt.bankaccount.domain.intf.Transaction;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateAccountResponse {
    private final Long id;
    private final double balance;
    private final String name;
    private final List<Transaction> transactions;

    public static CreateAccountResponse of(Account account) {
        return CreateAccountResponse.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .name(account.getName())
                .transactions(account.getTransactionHistory())
                .build();
    }
}
