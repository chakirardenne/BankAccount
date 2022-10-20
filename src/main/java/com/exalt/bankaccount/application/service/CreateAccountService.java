package com.exalt.bankaccount.application.service;

import com.exalt.bankaccount.adapters.in.dto.CreateAccountRequest;
import com.exalt.bankaccount.adapters.in.dto.CreateAccountResponse;
import com.exalt.bankaccount.application.ports.in.CreateAccountUseCase;
import com.exalt.bankaccount.application.ports.out.AccountRepository;
import com.exalt.bankaccount.domain.impl.AccountImpl;
import com.exalt.bankaccount.domain.intf.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAccountService implements CreateAccountUseCase {
    private final AccountRepository accountRepository;

    @Override
    public CreateAccountResponse create(CreateAccountRequest createAccountRequest) {
        Account account = new AccountImpl(createAccountRequest.getId(),
                createAccountRequest.getBalance(),
                createAccountRequest.getName());
        accountRepository.save(account);
        return CreateAccountResponse.of(account);
    }
}
