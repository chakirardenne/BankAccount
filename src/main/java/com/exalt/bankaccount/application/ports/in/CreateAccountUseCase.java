package com.exalt.bankaccount.application.ports.in;

import com.exalt.bankaccount.adapters.in.dto.CreateAccountRequest;
import com.exalt.bankaccount.adapters.in.dto.CreateAccountResponse;

public interface CreateAccountUseCase {
    CreateAccountResponse create(CreateAccountRequest createAccountRequest);
}
