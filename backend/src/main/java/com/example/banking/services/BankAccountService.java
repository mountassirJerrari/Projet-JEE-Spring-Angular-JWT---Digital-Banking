package com.example.banking.services;

import com.example.banking.dtos.*;
import com.example.banking.exceptions.BalanceNotSufficientException;
import com.example.banking.exceptions.BankAccountNotFoundException;
import com.example.banking.exceptions.CustomerNotFoundException;

import java.util.List;

/**
 * Service interface for bank account operations.
 */
public interface BankAccountService {
    /**
     * Save a new customer.
     *
     * @param customerDTO The customer data
     * @return The saved customer data
     */
    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    /**
     * Update an existing customer.
     *
     * @param customerDTO The customer data with updates
     * @return The updated customer data
     * @throws CustomerNotFoundException If the customer is not found
     */
    CustomerDTO updateCustomer(CustomerDTO customerDTO) throws CustomerNotFoundException;

    /**
     * Delete a customer by ID.
     *
     * @param customerId The ID of the customer to delete
     * @throws CustomerNotFoundException If the customer is not found
     */
    void deleteCustomer(Long customerId) throws CustomerNotFoundException;

    /**
     * Create a new current account.
     *
     * @param initialBalance The initial balance for the account
     * @param overdraft The overdraft limit for the account
     * @param customerId The ID of the customer who owns the account
     * @return The created current account data
     * @throws CustomerNotFoundException If the customer is not found
     */
    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overdraft, Long customerId) throws CustomerNotFoundException;

    /**
     * Create a new savings account.
     *
     * @param initialBalance The initial balance for the account
     * @param interestRate The interest rate for the account
     * @param customerId The ID of the customer who owns the account
     * @return The created savings account data
     * @throws CustomerNotFoundException If the customer is not found
     */
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;

    /**
     * Get a list of all customers.
     *
     * @return List of all customers
     */
    List<CustomerDTO> listCustomers();

    /**
     * Get a bank account by ID.
     *
     * @param accountId The ID of the account
     * @return The bank account data
     * @throws BankAccountNotFoundException If the account is not found
     */
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;

    /**
     * Debit (withdraw) money from an account.
     *
     * @param accountId The ID of the account
     * @param amount The amount to debit
     * @param description Description of the operation
     * @throws BankAccountNotFoundException If the account is not found
     * @throws BalanceNotSufficientException If the account has insufficient balance
     */
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;

    /**
     * Credit (deposit) money to an account.
     *
     * @param accountId The ID of the account
     * @param amount The amount to credit
     * @param description Description of the operation
     * @throws BankAccountNotFoundException If the account is not found
     */
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;

    /**
     * Transfer money between accounts.
     *
     * @param accountIdSource The ID of the source account
     * @param accountIdDestination The ID of the destination account
     * @param amount The amount to transfer
     * @throws BankAccountNotFoundException If either account is not found
     * @throws BalanceNotSufficientException If the source account has insufficient balance
     */
    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    /**
     * Get a list of bank accounts for a customer.
     *
     * @param customerId The ID of the customer
     * @return List of bank accounts owned by the customer
     */
    List<BankAccountDTO> getBankAccountsByCustomer(Long customerId);

    /**
     * Get a customer by ID.
     *
     * @param customerId The ID of the customer
     * @return The customer data
     * @throws CustomerNotFoundException If the customer is not found
     */
    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

    /**
     * Get account operations history with pagination.
     *
     * @param accountId The ID of the account
     * @param page The page number (0-based)
     * @param size The page size
     * @return Account history with operations
     * @throws BankAccountNotFoundException If the account is not found
     */
    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;

    /**
     * Search for customers by name keyword.
     *
     * @param keyword The keyword to search for
     * @return List of matching customers
     */
    List<CustomerDTO> searchCustomers(String keyword);

    /**
     * Get a list of all bank accounts.
     *
     * @return List of all bank accounts
     */
    List<BankAccountDTO> bankAccountList();

    /**
     * Get account operations history without pagination.
     *
     * @param accountId The ID of the account
     * @return List of account operations
     */
    List<AccountOperationDTO> accountHistory(String accountId);
}
