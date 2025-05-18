package com.example.banking.repositories;

import com.example.banking.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    /**
     * Find all bank accounts with balance between minBalance and maxBalance.
     *
     * @param minBalance The minimum balance
     * @param maxBalance The maximum balance
     * @return List of bank accounts within the balance range
     */
    List<BankAccount> findByBalanceBetween(double minBalance, double maxBalance);

    /**
     * Find all bank accounts owned by customers with names containing the given keyword.
     *
     * @param name The customer name keyword
     * @return List of bank accounts owned by matching customers
     */
    @Query("SELECT b FROM BankAccount b JOIN b.customer c WHERE UPPER(c.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    List<BankAccount> findByCustomerNameContainsIgnoreCase(@Param("name") String name);
}
