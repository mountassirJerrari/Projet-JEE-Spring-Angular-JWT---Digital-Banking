package com.example.banking.mappers;

import com.example.banking.dtos.AccountOperationDTO;
import com.example.banking.entities.AccountOperation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper interface for converting between AccountOperation entity and AccountOperationDTO.
 */
@Mapper(componentModel = "spring")
public interface AccountOperationMapper {
    /**
     * Convert from AccountOperation entity to AccountOperationDTO.
     *
     * @param accountOperation The AccountOperation entity
     * @return The corresponding AccountOperationDTO
     */
    AccountOperationDTO fromAccountOperation(AccountOperation accountOperation);
    
    /**
     * Convert a list of AccountOperation entities to a list of AccountOperationDTOs.
     *
     * @param accountOperations The list of AccountOperation entities
     * @return The corresponding list of AccountOperationDTOs
     */
    List<AccountOperationDTO> fromAccountOperations(List<AccountOperation> accountOperations);
}
