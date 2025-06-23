package arjunSingh.JobApplication.Review.Impl;

import arjunSingh.JobApplication.Company.Company;
import arjunSingh.JobApplication.Review.Review;
import arjunSingh.JobApplication.Review.ReviewRepository;
import arjunSingh.JobApplication.Review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews ;
    }



}
