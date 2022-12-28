package com.example.backend.repository;

import com.example.backend.entities.Product;
import com.example.backend.entities.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductLineRepo extends JpaRepository<ProductLine, Long> {
}
