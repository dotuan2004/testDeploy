package com.example.demo.presentation.model.request.DiscountCode;

import lombok.Data;

@Data
public class PostCreateDiscountCode {
    private Integer id;
    private String code;
    private Double discount;
    private Double minOrderValue;
    private Double maxDiscountAmount;
    private Boolean active;
}
