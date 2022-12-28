package com.example.backend.repository;

import com.example.backend.entities.Product;
import com.example.backend.entities.Quarter;
import com.example.backend.entities.WarrantyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarrantyInfoRepo extends JpaRepository<WarrantyInfo, Long> {
    List<WarrantyInfo> findByProduct(Product product);
    List<WarrantyInfo> findByWarrantyCenter(Quarter warrantyCenter);
    List<WarrantyInfo> findByShop(Quarter shop);
}
