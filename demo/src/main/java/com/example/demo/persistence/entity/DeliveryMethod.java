package com.example.demo.persistence.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "deliverymethod")
public class DeliveryMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_method_id")
    private Integer deliveryMethodId;

    @Column(name = "delivery_method_name")
    private String deliveryMethodName;

    @Column(name = "description")
    private String description;

    @Column(name = "delivery_cost")
    private Double deliveryCost;

    @OneToMany(mappedBy = "deliveryMethod", cascade = CascadeType.ALL)
    List<Order> orders;
}

