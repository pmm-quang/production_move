package com.example.backend.payload;

import com.example.backend.entities.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String status;
    private RoleDto role;
    private QuarterDto quarter;
}
