package com.example.banking.dtos;

import com.example.banking.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * DTO for SavingAccount entity.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SavingBankAccountDTO extends BankAccountDTO {
    private double interestRate;

    public SavingBankAccountDTO(String id, double balance, Date createdAt, AccountStatus status,
                               String createdBy, CustomerDTO customerDTO, double interestRate) {
        super(id, balance, createdAt, status, "SAVING", createdBy, customerDTO);
        this.interestRate = interestRate;
    }
}
