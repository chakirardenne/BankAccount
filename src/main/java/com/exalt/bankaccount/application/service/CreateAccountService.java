package com.exalt.bankaccount.application.service;

import com.exalt.bankaccount.adapters.in.dto.CreateAccountRequest;
import com.exalt.bankaccount.adapters.in.dto.CreateAccountResponse;
import com.exalt.bankaccount.application.ports.in.CreateAccountUseCase;
import com.exalt.bankaccount.application.ports.out.AccountRepository;
import com.exalt.bankaccount.domain.impl.AccountImpl;
import com.exalt.bankaccount.domain.intf.Account;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CreateAccountService implements CreateAccountUseCase {
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public CreateAccountResponse create(CreateAccountRequest createAccountRequest) {
        Account account = new AccountImpl(createAccountRequest.getId(),
                createAccountRequest.getBalance(),
                createAccountRequest.getName(),
                new ArrayList<>());
        accountRepository.save(account);
        return modelMapper.map(account, CreateAccountResponse.class);
    }
}
