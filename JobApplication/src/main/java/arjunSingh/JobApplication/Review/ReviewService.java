package arjunSingh.JobApplication.Review;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean addReview(Long companyId, Review review);
    Review getReview(Long companyId, Long review);
    Optional<Review> findById(Long reviewId);
    Review saveReview(Review review);
    void deleteReview(Long companyId, Long reviewId);
}
