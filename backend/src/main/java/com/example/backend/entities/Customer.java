package com.example.backend.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
public class Customer {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    private String name;
    private String sex;
    @Column(name = "yob")
    private Integer yearOfBirth;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Order> orders;

}
