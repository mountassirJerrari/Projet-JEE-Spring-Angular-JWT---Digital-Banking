package com.example.banking.mappers;

import com.example.banking.dtos.*;
import com.example.banking.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper interface for converting between BankAccount entities and DTOs.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface BankAccountMapper {
    /**
     * Convert from CurrentAccount entity to CurrentBankAccountDTO.
     *
     * @param currentAccount The CurrentAccount entity
     * @return The corresponding CurrentBankAccountDTO
     */
    @Mapping(source = "customer", target = "customerDTO")
    @Mapping(target = "type", constant = "CURRENT")
    CurrentBankAccountDTO fromCurrentAccount(CurrentAccount currentAccount);
    
    /**
     * Convert from SavingAccount entity to SavingBankAccountDTO.
     *
     * @param savingAccount The SavingAccount entity
     * @return The corresponding SavingBankAccountDTO
     */
    @Mapping(source = "customer", target = "customerDTO")
    @Mapping(target = "type", constant = "SAVING")
    SavingBankAccountDTO fromSavingAccount(SavingAccount savingAccount);
    
    /**
     * Convert from BankAccount entity to BankAccountDTO.
     * This method will determine the specific type of account and convert accordingly.
     *
     * @param bankAccount The BankAccount entity
     * @return The corresponding BankAccountDTO
     */
    default BankAccountDTO fromBankAccount(BankAccount bankAccount) {
        if (bankAccount instanceof SavingAccount) {
            return fromSavingAccount((SavingAccount) bankAccount);
        } else if (bankAccount instanceof CurrentAccount) {
            return fromCurrentAccount((CurrentAccount) bankAccount);
        }
        return null;
    }
}
