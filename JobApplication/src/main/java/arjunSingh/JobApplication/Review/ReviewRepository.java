package arjunSingh.JobApplication.Review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByCompanyId(Long companyId);
    Optional<Review> findByIdAndCompany_Id(Long reviewId,Long companyId);
}
