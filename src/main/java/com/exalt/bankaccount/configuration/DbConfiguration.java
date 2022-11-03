package com.exalt.bankaccount.configuration;

import com.exalt.bankaccount.adapters.out.db.H2DbAdapter;
import com.exalt.bankaccount.adapters.out.db.SpringDataJpaRepository;
import com.exalt.bankaccount.application.converter.MapperTool;
import com.exalt.bankaccount.application.converter.MapperToolImpl;
import com.exalt.bankaccount.application.ports.out.AccountPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = SpringDataJpaRepository.class)
public class DbConfiguration {
    @Bean
    AccountPort accountRepository(SpringDataJpaRepository springDataJpaRepository, MapperTool mapperTool) {
        return new H2DbAdapter(springDataJpaRepository, mapperTool);
    }

    @Bean
    MapperTool mapper() {
        return new MapperToolImpl();
    }
}