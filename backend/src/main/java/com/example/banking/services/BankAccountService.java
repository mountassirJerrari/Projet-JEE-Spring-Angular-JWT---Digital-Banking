package com.example.banking.services;

import com.example.banking.dtos.*;
import com.example.banking.exceptions.BalanceNotSufficientException;
import com.example.banking.exceptions.BankAccountNotFoundException;
import com.example.banking.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(CustomerDTO customerDTO) throws CustomerNotFoundException;
    void deleteCustomer(Long customerId) throws CustomerNotFoundException;
    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overdraft, Long customerId) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<CustomerDTO> listCustomers();
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;
    List<BankAccountDTO> getBankAccountsByCustomer(Long customerId);
    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;
    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;
    List<CustomerDTO> searchCustomers(String keyword);
    List<BankAccountDTO> bankAccountList();
    List<AccountOperationDTO> accountHistory(String accountId);
    List<BankAccountDTO> searchAccountsByBalanceRange(double minBalance, double maxBalance);
    List<BankAccountDTO> searchAccountsByCustomerName(String customerName);
}
