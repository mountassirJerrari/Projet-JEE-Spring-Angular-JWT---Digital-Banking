package com.example.banking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entity representing a bank customer.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    /**
     * Username of the user who created this customer.
     */
    private String createdBy;

    /**
     * Date when this customer was created.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    /**
     * List of bank accounts owned by this customer.
     */
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<BankAccount> accounts;
}
