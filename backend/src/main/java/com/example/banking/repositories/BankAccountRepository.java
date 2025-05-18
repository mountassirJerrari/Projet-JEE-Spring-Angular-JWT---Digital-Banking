package com.example.banking.repositories;

import com.example.banking.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    List<BankAccount> findByCustomerId(Long customerId);
    List<BankAccount> findByBalanceBetween(double minBalance, double maxBalance);

    @Query("SELECT b FROM BankAccount b JOIN b.customer c WHERE UPPER(c.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    List<BankAccount> findByCustomerNameContainsIgnoreCase(@Param("name") String name);
}
