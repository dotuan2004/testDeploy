package com.example.demo.presentation.model.request.Order;

import com.example.demo.persistence.entity.OrderDetail;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;
@Data
public class PostCreateOrderRequest {
    private Integer userId;
    private String billingAddress;
    private String shippingAddress;
    private Double totalAmount;
    private String paymentStatus;
    private String deliveryStatus;
    private String phoneNumberOrder;
    private List<PostCreateOrderDetailsRequest> orderDetails;
}
