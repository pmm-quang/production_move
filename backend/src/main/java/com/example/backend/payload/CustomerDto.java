package com.example.backend.payload;

import lombok.Data;

import javax.persistence.Column;

@Data
public class CustomerDto {
    private String id;
    private String name;
    private String sex;
    private Integer yearOfBirth;
    private String address;
    private String phone;
}
