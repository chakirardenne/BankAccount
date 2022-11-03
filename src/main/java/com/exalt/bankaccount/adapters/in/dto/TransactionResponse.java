package com.exalt.bankaccount.adapters.in.dto;

import lombok.Data;
import java.time.LocalDateTime;
@Data
public class TransactionResponse {
    private String type;
    private double amount;
    private LocalDateTime date;
    private double balance;
}
