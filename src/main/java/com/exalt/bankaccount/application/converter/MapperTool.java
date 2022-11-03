package com.exalt.bankaccount.application.converter;

import com.exalt.bankaccount.adapters.in.dto.CreateAccountResponse;
import com.exalt.bankaccount.adapters.in.dto.TransactionResponse;
import com.exalt.bankaccount.adapters.out.entities.AccountEntity;
import com.exalt.bankaccount.adapters.out.entities.TransactionEntity;
import com.exalt.bankaccount.domain.impl.AccountImpl;
import com.exalt.bankaccount.domain.impl.TransactionImpl;
import com.exalt.bankaccount.domain.intf.Account;
import com.exalt.bankaccount.domain.intf.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface MapperTool {
    @Mapping(target = "transactions", source = "transactionHistory")
    CreateAccountResponse accountToCreateAccountResponse(Account account);
    @Mapping(target = "transactions", source = "transactionHistory")
    AccountEntity accountToAccountEntity(Account account);
    @Mapping(target = "type", source = "type")
    TransactionResponse transactionToTransactionResponse(Transaction transaction);
    List<TransactionEntity> listTransactionToListTransactionEntity(List<Transaction> transactions);
    TransactionImpl transactionEntityToTransaction(TransactionEntity transactionEntity);
    AccountImpl accountEntityToAccount(AccountEntity accountEntity);
}
