package com.example.banking.mappers;

import com.example.banking.dtos.AccountOperationDTO;
import com.example.banking.entities.AccountOperation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountOperationMapper {
    AccountOperationDTO fromAccountOperation(AccountOperation accountOperation);
    List<AccountOperationDTO> fromAccountOperations(List<AccountOperation> accountOperations);
}
