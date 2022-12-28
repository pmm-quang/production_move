package com.example.backend.repository;

import com.example.backend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, String> {
    List<Customer> findAllById(String longs);
}
