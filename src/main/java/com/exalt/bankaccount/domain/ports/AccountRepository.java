package com.exalt.bankaccount.domain.ports;

import com.exalt.bankaccount.domain.model.intf.Account;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findById(Long name);
    void save(Account account);
}
