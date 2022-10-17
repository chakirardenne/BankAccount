package com.exalt.bankaccount.configuration;

import com.exalt.bankaccount.adapters.out.db.SpringDataJpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = SpringDataJpaRepository.class)
public class MySQLDbConfiguration{
}
