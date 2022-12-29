package com.example.backend.repository;

import com.example.backend.entities.Customer;
import com.example.backend.entities.Order;
import com.example.backend.entities.Product;
import com.example.backend.entities.custom.OrderByYear;
import com.example.backend.entities.custom.OrderPerMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(Customer customer);
    List<Order> findByProduct(Product product);

/** SHOP*/
    @Query("SELECT new com.example.backend.entities.custom.OrderPerMonth(month(o.buyDate), count(o.id)) FROM Order AS o " +
            "JOIN Product p on  p.id = o.product " +
            "WHERE year(o.buyDate) = :year and p.shop.id = :shopID GROUP BY month(o.buyDate) order by month(o.buyDate)")
//    @Query("SELECT new com.example.backend.entities.custom.OrderPerMonth(o.id, o.id) from Order as o")
    List<OrderPerMonth> countOrdersPerMonthOfShop(@Param("year") Integer year, @Param("shopID") Long shopID);

    @Query("SELECT new com.example.backend.entities.custom.OrderByYear(year(o.buyDate) , count(o.id)) FROM Order AS o " +
            "JOIN Product p on  p.id = o.product WHERE p.shop.id = :shopID " +
            "GROUP BY year(o.buyDate) order by year(o.buyDate)")
    List<OrderByYear> countOrdersPerYearOfShop(@Param("shopID") Long shopID);


/** FACTORY*/
    @Query("SELECT new com.example.backend.entities.custom.OrderPerMonth(month(o.buyDate), count(o.id)) FROM Order AS o " +
            "JOIN Product p on  p.id = o.product " +
            "WHERE year(o.buyDate) = :year and p.factory.id = :factoryID GROUP BY month(o.buyDate) order by month(o.buyDate)")
    List<OrderPerMonth> countOrdersPerMonthOfFactory(@Param("year") Integer year, @Param("factoryID") Long factoryID);

    @Query("SELECT new com.example.backend.entities.custom.OrderByYear(year(o.buyDate) , count(o.id)) FROM Order AS o " +
            "JOIN Product p on  p.id = o.product WHERE p.factory.id = :factoryID " +
            "GROUP BY year(o.buyDate) order by year(o.buyDate)")
    List<OrderByYear> countOrdersPerYearOfFactory(@Param("factoryID") Long factoryID);
}
