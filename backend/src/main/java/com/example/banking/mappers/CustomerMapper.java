package com.example.banking.mappers;

import com.example.banking.dtos.CustomerDTO;
import com.example.banking.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO fromCustomer(Customer customer);
    Customer toCustomer(CustomerDTO customerDTO);
}
