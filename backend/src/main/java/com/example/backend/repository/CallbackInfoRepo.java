package com.example.backend.repository;

import com.example.backend.entities.CallbackInfo;
import com.example.backend.entities.Product;
import com.example.backend.entities.Quarter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallbackInfoRepo extends JpaRepository<CallbackInfo, Long> {
    List<CallbackInfo> findByProduct(Product product);
    List<CallbackInfo> findByShop(Quarter shop);
    List<CallbackInfo> findByFactory(Quarter factory);
}
