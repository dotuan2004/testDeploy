package com.example.demo.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "billing_address")
    private String billingAddress;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "delivery_status")
    private String deliveryStatus;

    @Column(name = "phone_number_order")
    private String phoneNumberOrder;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "discount_code_id")
    private DiscountCode discountCode;

    @ManyToOne()
    @JoinColumn(name = "payment_method_id")
    PaymentMethod paymentMethod;

    @ManyToOne()
    @JoinColumn(name = "delivery_method_id")
    DeliveryMethod deliveryMethod;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    User user;
}
