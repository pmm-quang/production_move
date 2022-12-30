package com.example.backend.repository;

import com.example.backend.entities.Quarter;
import com.example.backend.entities.Role;
import com.example.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findByRole(Role role);
    List<User> findByQuarter(Quarter quarter);

    User findByUsername(String username);
}
