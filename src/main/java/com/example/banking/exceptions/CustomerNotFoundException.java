package com.example.banking.exceptions;

/**
 * Exception thrown when a customer is not found.
 */
public class CustomerNotFoundException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message
     */
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
