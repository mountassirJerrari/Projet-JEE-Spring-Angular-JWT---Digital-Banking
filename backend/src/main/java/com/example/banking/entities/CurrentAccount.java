package com.example.banking.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity representing a current (checking) account.
 * Extends the base BankAccount class.
 */
@Entity
@DiscriminatorValue("CURRENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CurrentAccount extends BankAccount {
    /**
     * The maximum amount that can be overdrawn from this account.
     */
    private double overdraft;
}
