package com.example.backend.payload;

import lombok.Data;

import java.sql.Date;

@Data
public class OrderDto {
    private Long id;
    private Date buyDate;
    private ProductDto product;
   private CustomerDto customer;
}
