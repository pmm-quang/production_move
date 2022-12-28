package com.example.backend.repository;

import com.example.backend.entities.Quarter;
import com.example.backend.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuarterRepo extends JpaRepository<Quarter, Long> {
    List<Quarter> findAllByRole(Role role);
}
