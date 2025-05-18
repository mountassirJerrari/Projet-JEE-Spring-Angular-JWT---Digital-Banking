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

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@RequiredArgsConstructor
@Slf4j
public class BankAccountRestController {
    private final BankAccountService bankAccountService;

    @GetMapping("/accounts/{accountId}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }

    @GetMapping("/accounts")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public List<BankAccountDTO> listAccounts() {
        return bankAccountService.bankAccountList();
    }

    @GetMapping("/accounts/{accountId}/operations")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId) {
        return bankAccountService.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(accountId, page, size);
    }

    @PostMapping("/accounts/debit")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankAccountService.debit(debitDTO.getAccountId(), debitDTO.getAmount(), debitDTO.getDescription());
        return debitDTO;
    }

    @PostMapping("/accounts/credit")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException {
        bankAccountService.credit(creditDTO.getAccountId(), creditDTO.getAmount(), creditDTO.getDescription());
        return creditDTO;
    }

    @PostMapping("/accounts/transfer")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public void transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankAccountService.transfer(
                transferRequestDTO.getAccountSource(),
                transferRequestDTO.getAccountDestination(),
                transferRequestDTO.getAmount());
    }

    @GetMapping("/accounts/customer/{customerId}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<BankAccountDTO> getAccountsByCustomer(@PathVariable Long customerId) {
        return bankAccountService.getBankAccountsByCustomer(customerId);
    }

    @GetMapping("/accounts/search/balance")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<BankAccountDTO> searchAccountsByBalanceRange(
            @RequestParam(name = "min", defaultValue = "0") double minBalance,
            @RequestParam(name = "max", defaultValue = "1000000") double maxBalance) {
        return bankAccountService.searchAccountsByBalanceRange(minBalance, maxBalance);
    }

    @GetMapping("/accounts/search/customer")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<BankAccountDTO> searchAccountsByCustomerName(
            @RequestParam(name = "name") String customerName) {
        return bankAccountService.searchAccountsByCustomerName(customerName);
    }

    @PostMapping("/accounts/current")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public CurrentBankAccountDTO createCurrentAccount(@RequestBody AccountRequestDTO accountRequestDTO) throws CustomerNotFoundException {
        return bankAccountService.saveCurrentBankAccount(
                accountRequestDTO.getInitialBalance(),
                accountRequestDTO.getOverdraft(),
                accountRequestDTO.getCustomerId());
    }

    @PostMapping("/accounts/saving")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public SavingBankAccountDTO createSavingAccount(@RequestBody AccountRequestDTO accountRequestDTO) throws CustomerNotFoundException {
        return bankAccountService.saveSavingBankAccount(
                accountRequestDTO.getInitialBalance(),
                accountRequestDTO.getInterestRate(),
                accountRequestDTO.getCustomerId());
    }
}
