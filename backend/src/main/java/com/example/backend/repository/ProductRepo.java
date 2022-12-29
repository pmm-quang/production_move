package com.example.backend.repository;

import com.example.backend.entities.Product;
import com.example.backend.entities.ProductLine;
import com.example.backend.entities.Quarter;
import com.example.backend.entities.custom.ErrorPerLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, String> {

    List<Product> findByProductLine(ProductLine productLine);
    List<Product> findAllById(String id);
    List<Product> findByEngineNumber(String engineNumber);

    List<Product> findByShopAndStatus(Quarter shop, String status);

    @Query("select new com.example.backend.entities.custom.ErrorPerLine(pl.model, count(c.id), count(p.id)) from Product as p " +
            "left join CallbackInfo c on p.id = c.product.id " +
            "join ProductLine pl on pl.id = p.productLine.id " +
            "where p.factory.id = :factoryID group by p.productLine.id")
    List<ErrorPerLine> findErrorPerLineOfFactory(@Param("factoryID") Long factoryID);

    @Query("select new com.example.backend.entities.custom.ErrorPerLine(q.name, count(c.id), count(p.id)) from Product as p " +
            "left join CallbackInfo c on p.id = c.product.id " +
            "join Quarter q on q.id = p.shop.id " +
            "where p.factory.id = :factoryID group by p.shop.id")
    List<ErrorPerLine> findErrorPerShopOfFactory(@Param("factoryID") Long factoryID);


}
