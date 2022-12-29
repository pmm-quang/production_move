package com.example.backend.controller;

import com.example.backend.payload.CustomerDto;
import com.example.backend.repository.CustomerRepo;
import com.example.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/vi/customer/")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> getAllCustomer() {
        List<CustomerDto> customerDtoList = customerService.getAllCustomer();
        return new ResponseEntity<List<CustomerDto>>(customerDtoList, HttpStatus.OK);
    }

    @GetMapping("/{customerID}")
    public ResponseEntity<CustomerDto> getCustomerByID(@PathVariable String customerID) {
        CustomerDto customerDto = customerService.findUserByID(customerID);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto customer = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("/update/{customerID}")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable String customerID) {
        CustomerDto customer = customerService.updateCustomer(customerID, customerDto);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
}
