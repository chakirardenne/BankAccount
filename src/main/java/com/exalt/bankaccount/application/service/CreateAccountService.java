package com.exalt.bankaccount.application.service;

import com.exalt.bankaccount.adapters.in.dto.CreateAccountRequest;
import com.exalt.bankaccount.adapters.in.dto.CreateAccountResponse;
import com.exalt.bankaccount.application.ports.in.CreateAccountUseCase;
import com.exalt.bankaccount.application.ports.out.AccountPort;
import com.exalt.bankaccount.domain.exception.IncorrectAmountValueInOperationException;
import com.exalt.bankaccount.domain.impl.AccountImpl;
import com.exalt.bankaccount.domain.impl.TransactionImpl;
import com.exalt.bankaccount.domain.impl.TransactionType;
import com.exalt.bankaccount.domain.intf.Account;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class CreateAccountService implements CreateAccountUseCase {
    private final AccountPort accountPort;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public CreateAccountResponse create(CreateAccountRequest createAccountRequest) {
        if(createAccountRequest.getBalance() <= 0)
            throw new IncorrectAmountValueInOperationException("Starting amount must be strictly positive");
        Account account = new AccountImpl(createAccountRequest.getBalance(),createAccountRequest.getName());
        account.addTransaction(new TransactionImpl(TransactionType.DEPOSIT, account.getBalance(),
                LocalDateTime.now(ZoneOffset.UTC), account.getBalance()));
        account = accountPort.save(account);
        return modelMapper.map(account, CreateAccountResponse.class);
    }
}
