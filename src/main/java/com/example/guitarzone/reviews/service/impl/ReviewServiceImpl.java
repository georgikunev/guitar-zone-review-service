package com.example.guitarzone.reviews.service.impl;

import com.example.guitarzone.reviews.model.Review;
import com.example.guitarzone.reviews.model.dtos.ReviewDTO;
import com.example.guitarzone.reviews.repositories.ReviewRepository;
import com.example.guitarzone.reviews.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addReview(ReviewDTO reviewDTO) {
        Review review = modelMapper.map(reviewDTO, Review.class);
        review.setCreatedDate(Instant.now());
        reviewRepository.save(review);
    }

    @Override
    public List<ReviewDTO> getReviewsByProductId(Long productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);
        return reviews.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public boolean hasUserReviewedProduct(Long userId, Long productId) {
        return reviewRepository.existsByUserIdAndProductId(userId, productId);
    }

    private ReviewDTO mapToDTO(Review review) {
//       ReviewDTO reviewDTO = new ReviewDTO();
//        reviewDTO.setId(reviewDTO.getId());
//        reviewDTO.setComment(review.getComment());
//        reviewDTO.setCreatedDate(review.getCreatedDate());      //use whichever
//        reviewDTO.setRating(review.getRating());
//        reviewDTO.setFullName(review.getFullName());
//        reviewDTO.setProductId(review.getProductId());
//        reviewDTO.setUserId(reviewDTO.getUserId());
//        return reviewDTO;
        return modelMapper.map(review, ReviewDTO.class);
    }
}
