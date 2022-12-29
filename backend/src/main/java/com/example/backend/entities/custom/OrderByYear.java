package com.example.backend.entities.custom;

import lombok.Data;

@Data
public class OrderByYear {
    private Object year;
    private Long count;

    public OrderByYear(Object year, Long count) {
        this.year = "Năm " + year;
        this.count = count;
    }
}
