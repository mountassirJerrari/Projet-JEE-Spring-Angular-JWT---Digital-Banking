package com.example.banking.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity representing a savings account.
 * Extends the base BankAccount class.
 */
@Entity
@DiscriminatorValue("SAVING")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SavingAccount extends BankAccount {
    /**
     * The interest rate applied to this savings account.
     */
    private double interestRate;
}
