package com.example.backend.payload;


import lombok.Data;

import java.sql.Date;

@Data
public class CallbackInfoDto {
    private Long id;
    private Date returnDate;
    private ProductDto product;
    private QuarterDto factory;
    private QuarterDto shop;
}
