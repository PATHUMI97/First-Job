package com.tutuu.firstjobapp.review;

import org.springframework.stereotype.Service;

import java.util.List;


public interface ReviewService {
    List<Review> findAllReview(Long companyId);
    boolean createReview(Long companyId, Review review);
    Review getReviewById(Long companyId, Long reviewId);
    boolean updateReviewById(Long companyId, Long reviewId, Review review);
    boolean deleteReview(Long companyId, Long reviewId);
}
