package com.example.demo.presentation.service;

import com.example.demo.DTO.ReviewDTO;
import com.example.demo.persistence.entity.Review;
import com.example.demo.persistence.entity.User;


public interface ReviewService {
    public Review saveDanhGia(ReviewDTO DTO, Integer bookID);

}
