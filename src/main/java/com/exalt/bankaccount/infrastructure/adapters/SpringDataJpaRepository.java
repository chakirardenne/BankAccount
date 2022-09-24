package com.exalt.bankaccount.infrastructure.adapters;

import com.exalt.bankaccount.infrastructure.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaRepository extends JpaRepository<AccountEntity, Long> {
}
