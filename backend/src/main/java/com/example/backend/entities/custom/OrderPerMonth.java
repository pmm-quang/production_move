package com.example.backend.entities.custom;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class OrderPerMonth {
    private Object month;
    private Long count;
    public OrderPerMonth(Object month, Long count) {
        this.month = "Tháng " + month;
        this.count = count;
    }
}
