package com.example.banking.dtos;

import com.example.banking.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * DTO for AccountOperation entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;
}
