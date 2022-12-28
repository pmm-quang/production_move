package com.example.backend.repository;

import com.example.backend.entities.Product;
import com.example.backend.entities.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, String> {

    List<Product> findByProductLine(ProductLine productLine);
    List<Product> findAllById(String id);
    List<Product> findByEngineNumber(String engineNumber);


}
