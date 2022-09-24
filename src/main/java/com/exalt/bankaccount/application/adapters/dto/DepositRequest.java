package com.exalt.bankaccount.application.adapters.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepositRequest {
    public Long id;
    public double amount;

}
