package com.example.backend.service.impl;

import com.example.backend.entities.Customer;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.payload.CustomerDto;
import com.example.backend.repository.CustomerRepo;
import com.example.backend.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    ModelMapper mapper;

    @Override
    public List<CustomerDto> getAllCustomer() {
        List<Customer> customerList = customerRepo.findAll();
        List<CustomerDto> customerDtoList = customerList.stream().map(customer -> mapper.map(customer, CustomerDto.class))
                                                                    .collect(Collectors.toList());
        return customerDtoList;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto newCustomer) {
        String id = newCustomer.getId();
        List<Customer> customers = customerRepo.findAllById(id);
        if (customers.isEmpty()) {
            Customer customer = mapper.map(newCustomer, Customer.class);
            customerRepo.save(customer);
            return mapper.map(customer, CustomerDto.class);
        }
        return null;
    }

    @Override
    public CustomerDto updateCustomer(String customerID, CustomerDto customerDto) {
        Customer customer = customerRepo.findById(customerID).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "CustomerID", Long.parseLong(customerID)));
        Customer newCustomer = mapper.map(customerDto, Customer.class);
        newCustomer.setId(customerID);
        customerRepo.save(newCustomer);
        return customerDto;
    }

    @Override
    public CustomerDto findUserByID(String customerID) {
        Customer customer = customerRepo.findById(customerID).orElseThrow(
                                        () -> new ResourceNotFoundException("Customer", "CustomerID", Long.parseLong(customerID)));
        return mapper.map(customer, CustomerDto.class);
    }
}
