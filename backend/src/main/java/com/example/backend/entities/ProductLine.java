package com.example.backend.entities;

import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "productline")
@Data
public class ProductLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String brand;
    private String model;
    private Double capacity;
    private Double length;
    private Double width;
    private Double height;
    private Double fuel;
    private String warrantyPeriod;

    @OneToMany(mappedBy = "productLine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;
}
