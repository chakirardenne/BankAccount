package com.exalt.bankaccount.domain.ports;

import com.exalt.bankaccount.domain.intf.Account;
import com.exalt.bankaccount.domain.intf.Transaction;

import java.util.List;

public interface AccountService {
    void deposit(Account account,  double amount);
    void withdraw(Account account, double amount);
    String getAccount(String accountName);
    List<Transaction> getHistory(Account account);
}
