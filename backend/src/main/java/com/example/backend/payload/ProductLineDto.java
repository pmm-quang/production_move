package com.example.backend.payload;

import lombok.Data;

@Data
public class ProductLineDto {
    private Long id;
    private String brand;
    private String model;
    private Double capacity;
    private Double length;
    private Double width;
    private Double height;
    private Double fuel;
    private String warrantyPeriod;
}
