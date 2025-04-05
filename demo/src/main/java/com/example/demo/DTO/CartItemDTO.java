package com.example.demo.DTO;

import lombok.Data;

@Data
public class CartItemDTO {
    private int id;
    private String tenSach;
    private double price;
    private int quantity;
    private String imageUrl;
}
