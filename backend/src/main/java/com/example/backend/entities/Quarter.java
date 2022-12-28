package com.example.backend.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "quater")
@Data
public class Quarter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    private String name;
    private String address;
    private String phone;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "quarter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "factory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

    @OneToMany( mappedBy = "shop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> productsShop;

    @OneToMany(mappedBy = "warrantyCenter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WarrantyInfo> warrantyInfoOfWC;

    @OneToMany( mappedBy = "shop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WarrantyInfo> warrantyInfoOfShop;

    @OneToMany(mappedBy = "factory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CallbackInfo> callbackInfoOfFactory;

    @OneToMany( mappedBy = "shop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CallbackInfo> callbackInfoOfShop;

}
