package com.example.backend.payload;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

@Data
public class CallbackInfoDto {
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date returnDate;
    private ProductDto product;
    private QuarterDto factory;
    private QuarterDto shop;
}
