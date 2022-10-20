package com.exalt.bankaccount.configuration;

import com.exalt.bankaccount.adapters.out.db.H2DbRepository;
import com.exalt.bankaccount.adapters.out.db.SpringDataJpaRepository;
import com.exalt.bankaccount.application.ports.out.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = SpringDataJpaRepository.class)
public class DbConfiguration {
    @Bean
    AccountRepository accountRepository(SpringDataJpaRepository springDataJpaRepository) {
        return new H2DbRepository(springDataJpaRepository);
    }
}