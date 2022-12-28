package com.example.backend.controller;

import com.example.backend.entities.User;
import com.example.backend.payload.UserDto;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<UserDto> userDtos = userService.getAllUser();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping("/{roleID}")
    public ResponseEntity<List<UserDto>> getUsersByRole(@PathVariable Long roleID) {
        List<UserDto> userDtos = userService.getUsersByRole(roleID);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping("/{quarterID}")
    public ResponseEntity<List<UserDto>> getUsersByQuarter(@PathVariable Long quarterID) {
        List<UserDto> userDtos = userService.getUserByQuarter(quarterID);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @PostMapping("/role/{roleID}/quarter/{quarterID}/add")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto,
                                              @PathVariable Long roleID,
                                              @PathVariable Long quarterID) {
        UserDto newUser = userService.createUser(userDto, roleID, quarterID);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/update/{userID}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,
                                              @PathVariable Long userID) {
        UserDto updateUser = userService.updateUser(userDto, userID);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

}
