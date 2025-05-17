package com.example.banking.mappers;

import com.example.banking.dtos.CustomerDTO;
import com.example.banking.entities.Customer;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between Customer entity and CustomerDTO.
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {
    /**
     * Convert from Customer entity to CustomerDTO.
     *
     * @param customer The Customer entity
     * @return The corresponding CustomerDTO
     */
    CustomerDTO fromCustomer(Customer customer);
    
    /**
     * Convert from CustomerDTO to Customer entity.
     *
     * @param customerDTO The CustomerDTO
     * @return The corresponding Customer entity
     */
    Customer toCustomer(CustomerDTO customerDTO);
}
