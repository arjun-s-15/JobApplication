package arjunSingh.JobApplication.Company.Impl;

import arjunSingh.JobApplication.Company.Company;
import arjunSingh.JobApplication.Company.CompanyRepository;
import arjunSingh.JobApplication.Company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CompanyServiceImpl implements CompanyService{

    private CompanyRepository companyRepository;

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void addCompany(Company company) {

    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean updateCompany(Long id, Company company) {
        return false;
    }
}