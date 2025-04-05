package com.example.demo.presentation.model.request.Book;

import lombok.Data;

@Data
public class PostCreateBookRequest {
    private String bookName;
    private String author;
    private String isBn;
    private String description;
    private Double listPrice;
    private Double salePrice;
    private Integer quantity;
    private String imageData;
}
