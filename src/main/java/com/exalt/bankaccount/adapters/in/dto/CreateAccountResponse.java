package com.exalt.bankaccount.adapters.in.dto;

import com.exalt.bankaccount.domain.intf.Transaction;
import lombok.Data;
import java.util.List;

@Data
public class CreateAccountResponse {
    private Long id;
    private double balance;
    private String name;
    private List<Transaction> transactions;
}
