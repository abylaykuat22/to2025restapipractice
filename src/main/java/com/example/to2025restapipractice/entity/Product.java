package com.example.to2025restapipractice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRODUCTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CODE", unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID")
    private Brand brand;
}
