package com.example.backend.service;


import com.example.backend.payload.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUser();
    List<UserDto> getUsersByRole(Long roleID);
    List<UserDto> getUserByQuarter(Long quarterID);
    UserDto createUser(UserDto userDto, Long roleID, Long quarterID);
    UserDto updateUser(UserDto userDto, Long userID);
}
