package com.example.backend.payload;

import com.example.backend.entities.CallbackInfo;
import com.example.backend.entities.Quarter;
import com.example.backend.entities.WarrantyInfo;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class ProductDto {
    private String id;
    private String engineNumber;
    private String color;
    private String status;
    private Date manufactureDate;
    private Integer warrantyTime;
    private QuarterDto factory;
    private QuarterDto shop;
    private ProductLineDto productLine;
}
