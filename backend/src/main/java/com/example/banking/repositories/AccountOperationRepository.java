package com.example.banking.repositories;

import com.example.banking.entities.AccountOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
    List<AccountOperation> findByBankAccountId(String accountId);
    Page<AccountOperation> findByBankAccountId(String accountId, Pageable pageable);
    List<AccountOperation> findByBankAccountIdOrderByOperationDateDesc(String accountId);
    void deleteByBankAccountId(String accountId);
}
