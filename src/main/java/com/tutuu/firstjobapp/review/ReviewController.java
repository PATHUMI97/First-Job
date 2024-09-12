package com.tutuu.firstjobapp.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> findAllReviews(@PathVariable Long companyId) {
        return new ResponseEntity<>(reviewService.findAllReview(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> CreateReview(@PathVariable Long companyId,
                                               @RequestBody Review review) {
        boolean isReviewAdded = reviewService.createReview(companyId, review);
        if (isReviewAdded) {
            return new ResponseEntity<>("review added", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("faild to add the review", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId,
                                                @PathVariable Long reviewId) {
        Review review = reviewService.getReviewById(companyId, reviewId);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review review) {
        boolean updated = reviewService.updateReviewById(companyId, reviewId, review);
        if (updated) {
            return new ResponseEntity<>("updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("update FAILED", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId) {
        boolean isDeleted = reviewService.deleteReview(companyId, reviewId);
        if (isDeleted) {
            return new ResponseEntity<>("review deletede successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
