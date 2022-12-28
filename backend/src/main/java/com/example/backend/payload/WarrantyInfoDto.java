package com.example.backend.payload;


import lombok.Data;

import java.sql.Date;

@Data
public class WarrantyInfoDto {
    private Long id;

    private Date warrantyDate;
    private Date completedDate;
    private Date returnDate;
    private Integer completed;
    private ProductDto product;
    private QuarterDto warrantyCenter;
    private QuarterDto shop;
}
