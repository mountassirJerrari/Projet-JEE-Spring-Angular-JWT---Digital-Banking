package com.example.banking.config;

import com.example.banking.dtos.CustomerDTO;
import com.example.banking.exceptions.BalanceNotSufficientException;
import com.example.banking.exceptions.BankAccountNotFoundException;
import com.example.banking.exceptions.CustomerNotFoundException;
import com.example.banking.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Stream;

/**
 * Configuration class for initializing test data.
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    /**
     * Initialize test data when the application starts.
     *
     * @param bankAccountService The bank account service
     * @return A CommandLineRunner that initializes the data
     */
    @Bean
    CommandLineRunner initData(BankAccountService bankAccountService) {
        return args -> {
            log.info("Initializing test data...");
            
            // Create customers
            Stream.of("John Doe", "Jane Smith", "Michael Johnson", "Emily Brown", "David Wilson")
                    .forEach(name -> {
                        CustomerDTO customer = new CustomerDTO();
                        customer.setName(name);
                        customer.setEmail(name.toLowerCase().replace(' ', '.') + "@example.com");
                        bankAccountService.saveCustomer(customer);
                    });
            
            // Get all customers
            List<CustomerDTO> customers = bankAccountService.listCustomers();
            
            // Create accounts for each customer
            for (CustomerDTO customer : customers) {
                try {
                    // Create a current account
                    bankAccountService.saveCurrentBankAccount(Math.random() * 90000, 1000, customer.getId());
                    
                    // Create a savings account
                    bankAccountService.saveSavingBankAccount(Math.random() * 120000, 3.5, customer.getId());
                    
                    // Get accounts for the customer
                    List<String> accountIds = bankAccountService.getBankAccountsByCustomer(customer.getId())
                            .stream()
                            .map(account -> account.getId())
                            .toList();
                    
                    // Perform some operations on the accounts
                    for (String accountId : accountIds) {
                        for (int i = 0; i < 5; i++) {
                            bankAccountService.credit(accountId, 1000 + Math.random() * 9000, "Credit operation");
                            bankAccountService.debit(accountId, 100 + Math.random() * 900, "Debit operation");
                        }
                    }
                    
                } catch (CustomerNotFoundException | BankAccountNotFoundException | BalanceNotSufficientException e) {
                    log.error("Error initializing data for customer {}: {}", customer.getId(), e.getMessage());
                }
            }
            
            log.info("Test data initialization completed.");
        };
    }
}
