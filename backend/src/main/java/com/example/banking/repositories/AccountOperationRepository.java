package com.example.banking.repositories;

import com.example.banking.entities.AccountOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for AccountOperation entity.
 */
public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
    /**
     * Find all operations for a specific bank account.
     *
     * @param accountId The ID of the bank account
     * @return List of operations for the account
     */
    List<AccountOperation> findByBankAccountId(String accountId);
    
    /**
     * Find operations for a specific bank account with pagination.
     *
     * @param accountId The ID of the bank account
     * @param pageable Pagination information
     * @return Page of operations for the account
     */
    Page<AccountOperation> findByBankAccountId(String accountId, Pageable pageable);
    
    /**
     * Find operations for a specific bank account ordered by date (descending).
     *
     * @param accountId The ID of the bank account
     * @return List of operations for the account ordered by date
     */
    List<AccountOperation> findByBankAccountIdOrderByOperationDateDesc(String accountId);
}
