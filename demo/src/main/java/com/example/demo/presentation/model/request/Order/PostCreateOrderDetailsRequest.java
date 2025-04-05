package com.example.demo.presentation.model.request.Order;

import lombok.Data;

@Data
public class PostCreateOrderDetailsRequest {
    private Integer bookId;
    private Integer quantity;
    private Double price;
}
