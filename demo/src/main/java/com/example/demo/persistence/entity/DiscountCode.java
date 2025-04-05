package com.example.demo.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    @Column(name = "discount_percentage")
    private Double discount;

    @Column(name = "min_order_value", nullable = false)
    private Double minOrderValue;

    @Column(name = "max_discount_amount")
    private Double maxDiscountAmount;

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "discountCode")
    private List<Order> orders;
}


