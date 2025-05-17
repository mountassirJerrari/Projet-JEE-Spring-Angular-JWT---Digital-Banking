package com.example.banking.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for debit operation request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebitDTO {
    private String accountId;
    private double amount;
    private String description;
}
