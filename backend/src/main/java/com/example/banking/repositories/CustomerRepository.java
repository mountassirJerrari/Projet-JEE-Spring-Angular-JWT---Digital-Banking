package com.example.banking.repositories;

import com.example.banking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface for Customer entity.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
     * Find customers by name containing the given keyword (case-insensitive).
     *
     * @param keyword The keyword to search for in customer names
     * @return List of matching customers
     */
    List<Customer> findByNameContainsIgnoreCase(String keyword);
    
    /**
     * Find a customer by their email address.
     *
     * @param email The email address to search for
     * @return The matching customer, if any
     */
    Customer findByEmail(String email);
}
