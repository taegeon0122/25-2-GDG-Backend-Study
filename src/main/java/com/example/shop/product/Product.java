package com.example.shop.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long id;

    @Column(name="product_name")
    private String name;

    @Column(name = "product_num")
    private Long num;

    @Column(name = "product_loginId")
    private Long loginId;

    public Product(Long id, String name, Long num, Long loginId) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.loginId = loginId;
    }
}
