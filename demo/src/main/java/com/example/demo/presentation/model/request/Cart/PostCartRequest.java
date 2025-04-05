package com.example.demo.presentation.model.request.Cart;

import lombok.Data;

@Data
public class PostCartRequest {
    private Integer id;
    private String username;
    private Integer bookId;
    private Integer userId;
}
