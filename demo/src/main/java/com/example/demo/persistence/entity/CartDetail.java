package com.example.demo.persistence.entity;

public class CartDetail {
    private Integer bookId;  // ID sách
    private Integer quantity;  // Số lượng
    private Double price;  // Giá

    // Constructor, Getters và Setters
    public CartDetail(Integer bookId, Integer quantity, Double price) {
        this.bookId = bookId;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
