package com.exalt.bankaccount.infrastructure.configuration;

import com.exalt.bankaccount.infrastructure.adapters.SpringDataJpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = SpringDataJpaRepository.class)
public class MySQLDbConfiguration{
}
