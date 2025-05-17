package com.example.banking.enums;

/**
 * Represents the possible states of a bank account.
 */
public enum AccountStatus {
    /**
     * Account has been created but not yet activated
     */
    CREATED,
    
    /**
     * Account is active and can be used for transactions
     */
    ACTIVATED,
    
    /**
     * Account has been suspended and cannot be used for transactions
     */
    SUSPENDED
}
