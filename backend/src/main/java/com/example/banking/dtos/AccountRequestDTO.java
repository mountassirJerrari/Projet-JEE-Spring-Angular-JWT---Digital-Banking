package com.example.banking.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDTO {
    private Long customerId;
    private double initialBalance;
    private double overdraft;
    private double interestRate;
}
