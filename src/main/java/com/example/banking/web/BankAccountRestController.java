package com.example.banking.web;

import com.example.banking.dtos.*;
import com.example.banking.exceptions.BalanceNotSufficientException;
import com.example.banking.exceptions.BankAccountNotFoundException;
import com.example.banking.exceptions.CustomerNotFoundException;
import com.example.banking.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for bank account-related operations.
 */
@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class BankAccountRestController {
    private final BankAccountService bankAccountService;

    /**
     * Get a bank account by ID.
     *
     * @param accountId The ID of the account
     * @return The bank account data
     * @throws BankAccountNotFoundException If the account is not found
     */
    @GetMapping("/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }

    /**
     * Get a list of bank accounts for a customer.
     *
     * @param customerId The ID of the customer
     * @return List of bank accounts owned by the customer
     */
    @GetMapping("/customer/{customerId}")
    public List<BankAccountDTO> getAccountsByCustomer(@PathVariable Long customerId) {
        return bankAccountService.getBankAccountsByCustomer(customerId);
    }

    /**
     * Create a new current account.
     *
     * @param initialBalance The initial balance for the account
     * @param overdraft The overdraft limit for the account
     * @param customerId The ID of the customer who owns the account
     * @return The created current account data
     * @throws CustomerNotFoundException If the customer is not found
     */
    @PostMapping("/current")
    public CurrentBankAccountDTO saveCurrentBankAccount(
            @RequestParam(name = "balance") double initialBalance,
            @RequestParam(name = "overdraft") double overdraft,
            @RequestParam(name = "customerId") Long customerId) throws CustomerNotFoundException {
        return bankAccountService.saveCurrentBankAccount(initialBalance, overdraft, customerId);
    }

    /**
     * Create a new savings account.
     *
     * @param initialBalance The initial balance for the account
     * @param interestRate The interest rate for the account
     * @param customerId The ID of the customer who owns the account
     * @return The created savings account data
     * @throws CustomerNotFoundException If the customer is not found
     */
    @PostMapping("/saving")
    public SavingBankAccountDTO saveSavingBankAccount(
            @RequestParam(name = "balance") double initialBalance,
            @RequestParam(name = "interestRate") double interestRate,
            @RequestParam(name = "customerId") Long customerId) throws CustomerNotFoundException {
        return bankAccountService.saveSavingBankAccount(initialBalance, interestRate, customerId);
    }

    /**
     * Debit (withdraw) money from an account.
     *
     * @param debitDTO The debit operation data
     * @throws BankAccountNotFoundException If the account is not found
     * @throws BalanceNotSufficientException If the account has insufficient balance
     */
    @PostMapping("/debit")
    public void debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankAccountService.debit(debitDTO.getAccountId(), debitDTO.getAmount(), debitDTO.getDescription());
    }

    /**
     * Credit (deposit) money to an account.
     *
     * @param creditDTO The credit operation data
     * @throws BankAccountNotFoundException If the account is not found
     */
    @PostMapping("/credit")
    public void credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException {
        bankAccountService.credit(creditDTO.getAccountId(), creditDTO.getAmount(), creditDTO.getDescription());
    }

    /**
     * Transfer money between accounts.
     *
     * @param transferRequestDTO The transfer operation data
     * @throws BankAccountNotFoundException If either account is not found
     * @throws BalanceNotSufficientException If the source account has insufficient balance
     */
    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankAccountService.transfer(
                transferRequestDTO.getAccountSource(),
                transferRequestDTO.getAccountDestination(),
                transferRequestDTO.getAmount());
    }

    /**
     * Get account operations history with pagination.
     *
     * @param accountId The ID of the account
     * @param page The page number (0-based)
     * @param size The page size
     * @return Account history with operations
     * @throws BankAccountNotFoundException If the account is not found
     */
    @GetMapping("/history/{accountId}")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(accountId, page, size);
    }
}
