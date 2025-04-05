package com.example.demo.presentation.service;

import com.example.demo.presentation.model.request.Order.PostCreateOrderRequest;

public interface OrderService {
    public void saveOrder(PostCreateOrderRequest request);
}
