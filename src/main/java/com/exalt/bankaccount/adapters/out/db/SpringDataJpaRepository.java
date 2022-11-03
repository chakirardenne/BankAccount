package com.exalt.bankaccount.adapters.out.db;

import com.exalt.bankaccount.adapters.out.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJpaRepository extends JpaRepository<AccountEntity, Long> {
}
