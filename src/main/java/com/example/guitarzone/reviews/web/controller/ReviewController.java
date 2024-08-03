package com.example.guitarzone.reviews.web.controller;

import com.example.guitarzone.reviews.model.dtos.ReviewDTO;
import com.example.guitarzone.reviews.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Void> addReview(@RequestBody ReviewDTO reviewDTO) {
        reviewService.addReview(reviewDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewService.getReviewsByProductId(productId));
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> hasUserReviewedProduct(@RequestParam Long userId, @RequestParam Long productId) {
        return ResponseEntity.ok(reviewService.hasUserReviewedProduct(userId, productId));
    }
}