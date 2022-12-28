package com.example.backend.service;

import com.example.backend.payload.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomer();
    CustomerDto createCustomer(CustomerDto newCustomer);
    CustomerDto updateCustomer(String customerID, CustomerDto customerDto);

    CustomerDto findUserByID(String customerID);

}
