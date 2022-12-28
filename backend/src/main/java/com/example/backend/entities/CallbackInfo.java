package com.example.backend.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "callbackinfo")
@Data
public class CallbackInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Date returnDate;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "factory_id")
    private Quarter factory;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "shop_id")
    private Quarter shop;
}
