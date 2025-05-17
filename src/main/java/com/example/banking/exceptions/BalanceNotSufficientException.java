package com.example.banking.exceptions;

/**
 * Exception thrown when an account has insufficient balance for an operation.
 */
public class BalanceNotSufficientException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message
     */
    public BalanceNotSufficientException(String message) {
        super(message);
    }
}
