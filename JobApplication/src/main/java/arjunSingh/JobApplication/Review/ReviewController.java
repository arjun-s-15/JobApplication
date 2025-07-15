package arjunSingh.JobApplication.Review;

import arjunSingh.JobApplication.Company.Company;
import arjunSingh.JobApplication.Company.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private final ReviewService reviewService;
    private final CompanyService companyService;

    public ReviewController(ReviewService reviewService,CompanyService companyService) {
        this.reviewService = reviewService;
        this.companyService = companyService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);

    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId ,@RequestBody Review review){
            reviewService.addReview(companyId, review);
            return new ResponseEntity<>("Review Added Successfully",HttpStatus.OK);

    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review review) {
        try {
            Optional<Company> companyOptional = companyService.findById(companyId);
            if (companyOptional.isEmpty()) {
                return new ResponseEntity<>("Company not present with id: " + companyId, HttpStatus.NOT_FOUND);
            }

            Optional<Review> existingRevOptional = reviewService.findById(reviewId);
            if (existingRevOptional.isEmpty()) {
                return new ResponseEntity<>("Review not found for company: " + companyId +
                        " with review id: " + reviewId, HttpStatus.NOT_FOUND);
            }

            Review existingReview = existingRevOptional.get();

            // Ensure review belongs to this company
            if (!existingReview.getCompany().getId().equals(companyId)) {
                return new ResponseEntity<>("Review does not belong to company id: " + companyId, HttpStatus.BAD_REQUEST);
            }

            // Update fields
            existingReview.setTitle(review.getTitle());
            existingReview.setRating(review.getRating());
            existingReview.setDescription(review.getDescription());
            existingReview.setCompany(companyOptional.get());

            reviewService.saveReview(existingReview);
            return new ResponseEntity<>("Review Updated Successfully", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId,
                                            @PathVariable Long reviewId) {
        return ResponseEntity.ok(reviewService.getReview(companyId, reviewId));
    }

    @DeleteMapping("reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
                                @PathVariable Long reviewId){
        reviewService.deleteReview(companyId,reviewId);

        return new ResponseEntity<>("review deleted for company id: "+companyId+" with review id "+reviewId,
                HttpStatus.OK);
    }




}
