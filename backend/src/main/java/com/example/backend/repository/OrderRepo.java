package com.example.backend.repository;

import com.example.backend.entities.Customer;
import com.example.backend.entities.Order;
import com.example.backend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(Customer customer);
    List<Order> findByProduct(Product product);
}
