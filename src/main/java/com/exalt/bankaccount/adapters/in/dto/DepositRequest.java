package com.exalt.bankaccount.adapters.in.dto;

import lombok.Data;

@Data
public class DepositRequest {
    private Long id;
    private double amount;
}
