package com.exalt.bankaccount.application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WithdrawRequest {
    public Long id;
    public double amount;
}
