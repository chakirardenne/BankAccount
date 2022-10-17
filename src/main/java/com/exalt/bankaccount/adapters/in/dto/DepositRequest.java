package com.exalt.bankaccount.adapters.in.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepositRequest {
    public Long id;
    public double amount;

}
