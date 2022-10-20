package com.exalt.bankaccount.adapters.in.dto;

import lombok.Data;

@Data
public class CreateAccountRequest {
    private Long id;
    private double balance;
    private String name;
}
