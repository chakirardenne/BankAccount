package com.exalt.bankaccount.infrastructure.configuration;

import com.exalt.bankaccount.domain.ports.AccountRepository;
import com.exalt.bankaccount.domain.service.BankAccountService;
import com.exalt.bankaccount.infrastructure.adapters.MySQLDbRepository;
import com.exalt.bankaccount.infrastructure.adapters.SpringDataJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    BankAccountService accountService(AccountRepository accountRepository) {
        return new BankAccountService(accountRepository);
    }
    @Bean
    AccountRepository accountRepository(SpringDataJpaRepository springDataJpaRepository) {
        return new MySQLDbRepository(springDataJpaRepository);
    }
}