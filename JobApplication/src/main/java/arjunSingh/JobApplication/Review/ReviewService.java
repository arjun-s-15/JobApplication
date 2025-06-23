package arjunSingh.JobApplication.Review;

import arjunSingh.JobApplication.Company.Company;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
//    Company addReview(Long companyId, Company company);
}
