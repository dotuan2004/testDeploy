package com.example.demo.presentation.service;

import java.util.Map;
import java.util.Set;

public interface CartService {

    // Lấy tất cả chi tiết giỏ hàng của người dùng
    Map<String, Object> getAllCartDetails(String userId);

    // Thêm sách vào giỏ hàng
    void addBookToCart(String userId, Integer bookId);
}

