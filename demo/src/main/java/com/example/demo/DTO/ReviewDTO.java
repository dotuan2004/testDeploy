package com.example.demo.DTO;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class ReviewDTO {
    private Integer rating;
    private String content;
    private String username;
    private Integer bookID;
}
