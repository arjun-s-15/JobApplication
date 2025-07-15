package arjunSingh.JobApplication.Review.Impl;

import arjunSingh.JobApplication.Company.Company;
import arjunSingh.JobApplication.Company.CompanyService;
import arjunSingh.JobApplication.Review.Review;
import arjunSingh.JobApplication.Review.ReviewRepository;
import arjunSingh.JobApplication.Review.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository,CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews ;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.findById(companyId)
                .orElseThrow(()->new IllegalArgumentException("Company not found with id: "+ companyId));
        review.setCompany(company);
        reviewRepository.save(review);
        return true;
    }

//    @Override
//    public ResponseEntity<Review> getReview(Long companyId, Long reviewId) {
//        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
//         return reviewRepository.findByCompanyId(companyId).stream()
//                .filter(r -> r.getId().equals(reviewId))
//                .findFirst()
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body(null));
//
//
//    }

    @Override
    public Optional<Review> findById(Long reviewId) {
        return reviewRepository.findById(reviewId);
    }

    @Override
    public Review saveReview(Review review) {
        return  reviewRepository.save(review);
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        return reviewRepository.findByIdAndCompany_Id(reviewId, companyId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found for this company"));
    }




    @Override
    public void deleteReview(Long companyId, Long reviewId) {
        Review review = reviewRepository.findByIdAndCompany_Id(reviewId, companyId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found for this company"));

        reviewRepository.delete(review);
    }

//    @Override
//    public boolean deleteReview(Long companyId, Long reviewId) {
//      return reviewRepository.findById(reviewId).
//              filter(r->r.getCompany().getId().equals(companyId)).
//              map(r->{
//                  reviewRepository.delete(r);
//                  return true;
//              }).orElse(false);
//
//    }
//@Override
//public boolean deleteReview(Long companyId, Long reviewId) {
//    if (companyService.findById(companyId).isPresent() &&reviewRepository.existsById(reviewId)){
//        reviewRepository.deleteById(reviewId);
//        return true;
//    }
//    return false;
//}
//


}
