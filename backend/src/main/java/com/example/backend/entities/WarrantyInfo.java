package com.example.backend.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "warrantyinfo")
@Getter
@Setter
public class WarrantyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Date warrantyDate;
    private Date completedDate;
    private Date returnDate;
    private Integer completed;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "warranty_id")
    private Quarter warrantyCenter;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "shop_id")
    private Quarter shop;

}
