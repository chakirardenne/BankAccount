package com.exalt.bankaccount.application.ports.out;

import com.exalt.bankaccount.domain.intf.Account;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findById(Long id);
    void save(Account account);
}
