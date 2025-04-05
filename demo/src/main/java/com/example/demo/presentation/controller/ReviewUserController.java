package com.example.demo.presentation.controller;

import com.example.demo.DTO.ReviewDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.persistence.Repository.ReviewRepository;
import com.example.demo.persistence.Repository.UserRepository;
import com.example.demo.persistence.entity.Review;
import com.example.demo.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reviews")
public class ReviewUserController {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/book/{maSach}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByBookId(@PathVariable Integer maSach) {
        List<Review> reviews = reviewRepository.findByBook_BookId(maSach);
        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ReviewDTO> reviewDTOs = reviews.stream().map(review -> {
            // Lấy thông tin User từ Review
            User user = review.getUser();

            // Tạo ReviewDTO
            return ReviewDTO.builder()
                    .rating(review.getRating())
                    .content(review.getContent())
                    .username(user != null ? user.getUsername() : "Unknown")
                    .bookID(maSach)
                    .build();
        }).collect(Collectors.toList());

        // Trả về danh sách ReviewDTO
        return ResponseEntity.ok(reviewDTOs);
    }
}