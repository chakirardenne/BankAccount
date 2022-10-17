package com.exalt.bankaccount.configuration;

import com.exalt.bankaccount.adapters.out.db.MySQLDbRepository;
import com.exalt.bankaccount.adapters.out.db.SpringDataJpaRepository;
import com.exalt.bankaccount.application.ports.out.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    AccountRepository accountRepository(SpringDataJpaRepository springDataJpaRepository) {
        return new MySQLDbRepository(springDataJpaRepository);
    }
}