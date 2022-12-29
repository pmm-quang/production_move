package com.example.backend.entities.custom;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorPerLine {
    private String line;
    private Long count;
    private Long total;
}
