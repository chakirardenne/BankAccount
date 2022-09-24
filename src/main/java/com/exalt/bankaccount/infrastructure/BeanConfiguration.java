package com.exalt.bankaccount.infrastructure;

import com.exalt.bankaccount.domain.ports.AccountRepository;
import com.exalt.bankaccount.domain.service.BankAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    BankAccountService accountService(AccountRepository accountRepository) {
        return new BankAccountService(accountRepository);
    }
}