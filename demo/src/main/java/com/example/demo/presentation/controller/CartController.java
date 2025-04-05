package com.example.demo.presentation.controller;
import com.example.demo.presentation.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add/{userId}/{bookId}")
    public String addBookToCart(@PathVariable String userId, @PathVariable Integer bookId) {
        log.info("logg{}",userId,bookId);
        cartService.addBookToCart(userId, bookId);
        return "Book added to cart";
    }


    // Lấy tất cả chi tiết giỏ hàng
    @GetMapping("/details/{userId}")
    public Map<String, Object> getAllCartDetails(@PathVariable String userId) {
        return cartService.getAllCartDetails(userId);
    }
}