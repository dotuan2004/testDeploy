package com.example.demo.presentation.controller;

import com.example.demo.DTO.ReviewDTO;
import com.example.demo.persistence.entity.Review;
import com.example.demo.presentation.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class SocKetController {
    @Autowired
    private ReviewService reviewService;
    @MessageMapping("/danhgia/{bookId}")
    @SendTo("/topic/sach-danhgia/{bookId}")
    public ReviewDTO handleReview(@DestinationVariable Integer bookId, ReviewDTO reviewDTO) {
        log.info("reviewDTO {}",reviewDTO);
        Review savedReview = reviewService.saveDanhGia(reviewDTO,bookId);
//        User user = reviewService.findUserReview(savedReview.getUser().getUserId());
        ReviewDTO response = ReviewDTO.builder().bookID(savedReview.getBook().getBookId())
                            .username(savedReview.getUser().getUsername())
                                    .rating(savedReview.getRating())
                                            .content(savedReview.getContent()).build();

        return response;
    }
}
