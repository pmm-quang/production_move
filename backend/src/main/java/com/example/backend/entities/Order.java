package com.example.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Date buyDate;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "product_id")
    private Product product;

}
