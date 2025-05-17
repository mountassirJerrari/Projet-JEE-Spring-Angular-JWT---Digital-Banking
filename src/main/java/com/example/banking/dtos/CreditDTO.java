package com.example.banking.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for credit operation request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditDTO {
    private String accountId;
    private double amount;
    private String description;
}
