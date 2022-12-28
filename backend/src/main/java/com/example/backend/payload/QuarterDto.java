package com.example.backend.payload;

import lombok.Data;

@Data
public class QuarterDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private RoleDto role;
}
