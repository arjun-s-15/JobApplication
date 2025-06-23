package arjunSingh.JobApplication.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> getAllCompanies();
    void createCompany(Company company);
    Optional<Company> findById(Long id);
    boolean deleteById(Long id);
    boolean updateCompany(Long id,Company company);

}
