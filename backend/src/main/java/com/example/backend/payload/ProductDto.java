package com.example.backend.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class ProductDto {
    private String id;
    private String engineNumber;
    private String color;
    private String status;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date manufactureDate;
    private QuarterDto factory;
    private QuarterDto shop;
    private ProductLineDto productLine;
}
