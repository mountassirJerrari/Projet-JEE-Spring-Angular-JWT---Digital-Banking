package com.example.banking.mappers;

import com.example.banking.dtos.*;
import com.example.banking.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface BankAccountMapper {
    @Mapping(source = "customer", target = "customerDTO")
    @Mapping(target = "type", constant = "CURRENT")
    CurrentBankAccountDTO fromCurrentAccount(CurrentAccount currentAccount);

    @Mapping(source = "customer", target = "customerDTO")
    @Mapping(target = "type", constant = "SAVING")
    SavingBankAccountDTO fromSavingAccount(SavingAccount savingAccount);

    default BankAccountDTO fromBankAccount(BankAccount bankAccount) {
        if (bankAccount instanceof SavingAccount) {
            return fromSavingAccount((SavingAccount) bankAccount);
        } else if (bankAccount instanceof CurrentAccount) {
            return fromCurrentAccount((CurrentAccount) bankAccount);
        }
        return null;
    }
}
