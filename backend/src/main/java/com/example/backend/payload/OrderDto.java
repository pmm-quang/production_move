package com.example.backend.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

@Data
public class OrderDto {
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date buyDate;
    private ProductDto product;
    private CustomerDto customer;
}
