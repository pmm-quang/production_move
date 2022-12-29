package com.example.backend.repository;

import com.example.backend.entities.Product;
import com.example.backend.entities.Quarter;
import com.example.backend.entities.WarrantyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarrantyInfoRepo extends JpaRepository<WarrantyInfo, Long> {
    List<WarrantyInfo> findByProduct(Product product);
    List<WarrantyInfo> findByWarrantyCenter(Quarter warrantyCenter);
    List<WarrantyInfo> findByShop(Quarter shop);

    @Query("select w from  WarrantyInfo w where month(w.warrantyDate) = :month and year(w.warrantyDate) = :year and w.warrantyCenter.id = :id")
    List<WarrantyInfo> findWarrantyInfoByMonthOfWarrantyCenter(@Param("id") Long id, @Param("year") Long year, @Param("month") Long month);

    @Query("select w from  WarrantyInfo w where ((month(w.warrantyDate) = :quarterPeriod*3) " +
            "or (month(w.warrantyDate) = :quarterPeriod*3 - 1) or (month(w.warrantyDate) = :quarterPeriod*3 - 2))" +
            "and year(w.warrantyDate) = :year and w.warrantyCenter.id = :id")
    List<WarrantyInfo> findWarrantyInfoByQuarterPeriodOfWarrantyCenter(@Param("id") Long id, @Param("year") Long year, @Param("quarterPeriod") Long quarterPeriod);

    @Query("select w from  WarrantyInfo w where year(w.warrantyDate) = :year and w.warrantyCenter.id = :id")
    List<WarrantyInfo> findWarrantyInfoByYearOfWarrantyCenter(@Param("id") Long id, @Param("year") Long year);
}
