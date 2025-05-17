package com.example.banking.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for transfer operation request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequestDTO {
    private String accountSource;
    private String accountDestination;
    private double amount;
    private String description;
}
