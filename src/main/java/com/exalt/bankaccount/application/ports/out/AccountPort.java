package com.exalt.bankaccount.application.ports.out;

import com.exalt.bankaccount.domain.intf.Account;
import com.exalt.bankaccount.domain.intf.Transaction;

import java.util.List;
import java.util.Optional;

public interface AccountPort {
    Optional<Account> findById(Long id);
    Account save(Account account);
    List<Transaction> getHistoryById(Long id);
}
