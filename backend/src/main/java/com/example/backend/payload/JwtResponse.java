package com.example.backend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class JwtResponse {
    private String jwt;
    private UserDto user;
    private List<String> role;
}
