package com.example.banking.entities;

import com.example.banking.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Entity representing an operation performed on a bank account.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Date operationDate;
    
    private double amount;
    
    private String description;
    
    @Enumerated(EnumType.STRING)
    private OperationType type;
    
    /**
     * The bank account on which this operation was performed.
     */
    @ManyToOne
    private BankAccount bankAccount;
}
