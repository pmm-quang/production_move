package com.example.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(cascade = {CascadeType.MERGE}, orphanRemoval = false)
    @JoinColumn(name = "product")
    private Product product;

}
