package com.example.banking.repositories;

import com.example.banking.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for BankAccount entity.
 */
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    /**
     * Find all bank accounts owned by a customer.
     *
     * @param customerId The ID of the customer
     * @return List of bank accounts owned by the customer
     */
    List<BankAccount> findByCustomerId(Long customerId);
}
