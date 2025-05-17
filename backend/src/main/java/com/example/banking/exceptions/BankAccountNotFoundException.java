package com.example.banking.exceptions;

/**
 * Exception thrown when a bank account is not found.
 */
public class BankAccountNotFoundException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message
     */
    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
