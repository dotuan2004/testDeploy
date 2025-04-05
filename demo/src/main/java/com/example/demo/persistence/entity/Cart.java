package com.example.demo.persistence.entity;
import java.util.Map;

public class Cart {
    private String userId;
    private Map<Integer, CartDetail> cartDetails;

    // Constructor, Getters và Setters
    public Cart(String userId, Map<Integer, CartDetail> cartDetails) {
        this.userId = userId;
        this.cartDetails = cartDetails;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<Integer, CartDetail> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(Map<Integer, CartDetail> cartDetails) {
        this.cartDetails = cartDetails;
    }
}