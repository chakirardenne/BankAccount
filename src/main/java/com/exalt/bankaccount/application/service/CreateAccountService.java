package com.exalt.bankaccount.application.service;

import com.exalt.bankaccount.adapters.in.dto.CreateAccountRequest;
import com.exalt.bankaccount.adapters.in.dto.CreateAccountResponse;
import com.exalt.bankaccount.application.converter.MapperTool;
import com.exalt.bankaccount.application.ports.in.CreateAccountUseCase;
import com.exalt.bankaccount.application.ports.out.AccountPort;
import com.exalt.bankaccount.domain.exception.IncorrectAmountValueInOperationException;
import com.exalt.bankaccount.domain.impl.AccountImpl;
import com.exalt.bankaccount.domain.impl.TransactionImpl;
import com.exalt.bankaccount.domain.impl.TransactionType;
import com.exalt.bankaccount.domain.intf.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CreateAccountService implements CreateAccountUseCase {
    private final AccountPort accountPort;
    private final MapperTool mapperTool;

    @Transactional
    @Override
    public CreateAccountResponse create(CreateAccountRequest createAccountRequest) {
        if(createAccountRequest.getBalance() <= 0)
            throw new IncorrectAmountValueInOperationException("Starting amount must be strictly positive");
        Account account = new AccountImpl(null, createAccountRequest.getBalance(),
                createAccountRequest.getName(), new ArrayList<>());
        account.addTransaction(new TransactionImpl(TransactionType.DEPOSIT, account.getBalance(),
                LocalDateTime.now(ZoneOffset.UTC), account.getBalance()));
        account = accountPort.save(account);
        return mapperTool.accountToCreateAccountResponse(account);
    }
}
