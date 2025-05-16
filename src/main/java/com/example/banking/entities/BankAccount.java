package com.example.banking.entities;

import com.example.banking.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Abstract entity representing a bank account.
 * This is the base class for different types of bank accounts.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 10)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BankAccount {
    @Id
    private String id;
    
    private double balance;
    
    private Date createdAt;
    
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    
    /**
     * The customer who owns this account.
     */
    @ManyToOne
    private Customer customer;
    
    /**
     * List of operations performed on this account.
     */
    @OneToMany(mappedBy = "bankAccount", fetch = FetchType.LAZY)
    private List<AccountOperation> operations;
}
