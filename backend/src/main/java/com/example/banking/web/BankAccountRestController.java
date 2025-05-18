package com.example.banking.web;

import com.example.banking.dtos.*;
import com.example.banking.exceptions.BalanceNotSufficientException;
import com.example.banking.exceptions.BankAccountNotFoundException;
import com.example.banking.exceptions.CustomerNotFoundException;
import com.example.banking.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for bank account-related operations.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@RequiredArgsConstructor
@Slf4j
public class BankAccountRestController {
    private final BankAccountService bankAccountService;

    /**
     * Get a bank account by ID.
     *
     * @param accountId The ID of the account
     * @return The bank account data
     * @throws BankAccountNotFoundException If the account is not found
     */
    @GetMapping("/accounts/{accountId}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }

    /**
     * Get a list of all bank accounts.
     *
     * @return List of all bank accounts
     */
    @GetMapping("/accounts")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public List<BankAccountDTO> listAccounts() {
        return bankAccountService.bankAccountList();
    }

    /**
     * Get account operations history without pagination.
     *
     * @param accountId The ID of the account
     * @return List of account operations
     */
    @GetMapping("/accounts/{accountId}/operations")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId) {
        return bankAccountService.accountHistory(accountId);
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
    @GetMapping("/accounts/{accountId}/pageOperations")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(accountId, page, size);
    }

    /**
     * Debit (withdraw) money from an account.
     *
     * @param debitDTO The debit operation data
     * @return The debit operation data
     * @throws BankAccountNotFoundException If the account is not found
     * @throws BalanceNotSufficientException If the account has insufficient balance
     */
    @PostMapping("/accounts/debit")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankAccountService.debit(debitDTO.getAccountId(), debitDTO.getAmount(), debitDTO.getDescription());
        return debitDTO;
    }

    /**
     * Credit (deposit) money to an account.
     *
     * @param creditDTO The credit operation data
     * @return The credit operation data
     * @throws BankAccountNotFoundException If the account is not found
     */
    @PostMapping("/accounts/credit")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException {
        bankAccountService.credit(creditDTO.getAccountId(), creditDTO.getAmount(), creditDTO.getDescription());
        return creditDTO;
    }

    /**
     * Transfer money between accounts.
     *
     * @param transferRequestDTO The transfer operation data
     * @throws BankAccountNotFoundException If either account is not found
     * @throws BalanceNotSufficientException If the source account has insufficient balance
     */
    @PostMapping("/accounts/transfer")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public void transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankAccountService.transfer(
                transferRequestDTO.getAccountSource(),
                transferRequestDTO.getAccountDestination(),
                transferRequestDTO.getAmount());
    }

    /**
     * Get a list of bank accounts for a customer.
     *
     * @param customerId The ID of the customer
     * @return List of bank accounts owned by the customer
     */
    @GetMapping("/accounts/customer/{customerId}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<BankAccountDTO> getAccountsByCustomer(@PathVariable Long customerId) {
        return bankAccountService.getBankAccountsByCustomer(customerId);
    }

    /**
     * Search for bank accounts by balance range.
     *
     * @param minBalance The minimum balance
     * @param maxBalance The maximum balance
     * @return List of bank accounts within the balance range
     */
    @GetMapping("/accounts/search/balance")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<BankAccountDTO> searchAccountsByBalanceRange(
            @RequestParam(name = "min", defaultValue = "0") double minBalance,
            @RequestParam(name = "max", defaultValue = "1000000") double maxBalance) {
        return bankAccountService.searchAccountsByBalanceRange(minBalance, maxBalance);
    }

    /**
     * Search for bank accounts by customer name.
     *
     * @param customerName The customer name to search for
     * @return List of bank accounts owned by customers with matching names
     */
    @GetMapping("/accounts/search/customer")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<BankAccountDTO> searchAccountsByCustomerName(
            @RequestParam(name = "name") String customerName) {
        return bankAccountService.searchAccountsByCustomerName(customerName);
    }

    /**
     * Create a new current account.
     *
     * @param accountRequestDTO The account creation data
     * @return The created current account
     * @throws CustomerNotFoundException If the customer is not found
     */
    @PostMapping("/accounts/current")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public CurrentBankAccountDTO createCurrentAccount(@RequestBody AccountRequestDTO accountRequestDTO) throws CustomerNotFoundException {
        return bankAccountService.saveCurrentBankAccount(
                accountRequestDTO.getInitialBalance(),
                accountRequestDTO.getOverdraft(),
                accountRequestDTO.getCustomerId());
    }

    /**
     * Create a new saving account.
     *
     * @param accountRequestDTO The account creation data
     * @return The created saving account
     * @throws CustomerNotFoundException If the customer is not found
     */
    @PostMapping("/accounts/saving")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public SavingBankAccountDTO createSavingAccount(@RequestBody AccountRequestDTO accountRequestDTO) throws CustomerNotFoundException {
        return bankAccountService.saveSavingBankAccount(
                accountRequestDTO.getInitialBalance(),
                accountRequestDTO.getInterestRate(),
                accountRequestDTO.getCustomerId());
    }
}
