package com.tutuu.firstjobapp.review;

import com.tutuu.firstjobapp.company.Company;
import com.tutuu.firstjobapp.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CompanyService companyService;

    @Override
    public List<Review> findAllReview(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean createReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
//        Company company = companyService.getCompanyById(companyId);
//        if (company != null){
//           return reviewRepository.findById(reviewId).orElse(null);
//        }
//        return null;

        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReviewById(Long companyId, Long reviewId, Review updatedReview) {
//        Company company = companyService.getCompanyById(companyId);
//        if (company != null){
//            Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
//            if (reviewOptional.isPresent()){
//                Review review = reviewOptional.get();
//                review.setTitle(updatedReview.getTitle());
//                review.setDescription(updatedReview.getDescription());
//                review.setRating(updatedReview.getRating());
//                reviewRepository.save(review);
//                return true;
//            }
//        }
//        return false;

        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            updatedReview.setCompany(company);
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        Company company = companyService.getCompanyById(companyId);
        boolean isExists = reviewRepository.existsById(reviewId);
        if (company != null && isExists) {
            Review review1 = reviewRepository.findById(reviewId).orElse(null);
            Company company1 = review1.getCompany();
            company1.getReviews().remove(review1);
            review1.setCompany(null);
            companyService.updateCompany(companyId,company1);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
