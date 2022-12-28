package com.example.backend.service.impl;

import com.example.backend.entities.Quarter;
import com.example.backend.entities.Role;
import com.example.backend.entities.User;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.payload.UserDto;
import com.example.backend.repository.QuarterRepo;
import com.example.backend.repository.RoleRepo;
import com.example.backend.repository.UserRepo;
import com.example.backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private  RoleRepo roleRepo;

    @Autowired
    private QuarterRepo quarterRepo;

    public UserServiceImpl(UserRepo userRepo,
                           RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public List<UserDto> getUsersByRole(Long roleID) {
        Role role = roleRepo.findById(roleID).orElseThrow(
                ()-> new ResourceNotFoundException("Role", "RoleID", roleID)
        );
        List<User> users = userRepo.findByRole(role);
        List<UserDto> userDtos = users.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public List<UserDto> getUserByQuarter(Long quarterID) {
        Quarter quarter = quarterRepo.findById(quarterID).orElseThrow(
                ()-> new ResourceNotFoundException("Quarter", "QuarterID", quarterID)
        );
        List<User> users = userRepo.findByQuarter(quarter);
        List<UserDto> userDtos = users.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public UserDto createUser(UserDto userDto, Long roleID, Long quarterID) {
        Role role = roleRepo.findById(roleID).orElseThrow(
                ()-> new ResourceNotFoundException("Role", "RoleID", roleID)
        );
        Quarter quarter = quarterRepo.findById(quarterID).orElseThrow(
                ()-> new ResourceNotFoundException("Quarter", "QuarterID", quarterID)
        );
        User user = mapper.map(userDto, User.class);
        User newUser = userRepo.save(user);
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userID) {
        User user = userRepo.findById(userID).orElseThrow(
                ()-> new ResourceNotFoundException("User", "UserID", userID)
        );
        User user1 = mapper.map(userDto, User.class);
        User updateUser = userRepo.save(user1);
        return userDto;
    }

}
