package com.example.backend.payload;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

@Data
public class WarrantyInfoDto {
    private Long id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date warrantyDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date completedDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date returnDate;
    private Integer completed;
    private ProductDto product;
    private QuarterDto warrantyCenter;
    private QuarterDto shop;
}
