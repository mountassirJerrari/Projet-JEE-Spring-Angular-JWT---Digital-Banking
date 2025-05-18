package com.example.banking.dtos;

import com.example.banking.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * DTO for CurrentAccount entity.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CurrentBankAccountDTO extends BankAccountDTO {
    private double overdraft;

    public CurrentBankAccountDTO(String id, double balance, Date createdAt, AccountStatus status,
                                String createdBy, CustomerDTO customerDTO, double overdraft) {
        super(id, balance, createdAt, status, "CURRENT", createdBy, customerDTO);
        this.overdraft = overdraft;
    }
}
