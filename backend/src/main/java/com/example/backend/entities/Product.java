package com.example.backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    private String engineNumber;
    private String color;
    private String status;

    private Date manufactureDate;


    @ManyToOne(cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "productline_id")
    private ProductLine productLine;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "factory_id")
    private Quarter factory;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "shop_id")
    private Quarter shop;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<WarrantyInfo> warrantyInfos;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CallbackInfo> callbackInfos;
}
