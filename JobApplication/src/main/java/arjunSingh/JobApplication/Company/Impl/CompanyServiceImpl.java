package arjunSingh.JobApplication.Company.Impl;

import arjunSingh.JobApplication.Company.Company;
import arjunSingh.JobApplication.Company.CompanyRepository;
import arjunSingh.JobApplication.Company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
        else{
            return false;}
    }

    @Override
    public boolean updateCompany(Long id, Company company) {
        return companyRepository.findById(id).map(existingCompany ->{
            existingCompany.setDescription(company.getDescription());
            existingCompany.setName(company.getDescription());
            existingCompany.setJobs(company.getJobs());
            companyRepository.save(existingCompany);
            return true;
        }).orElse(false);

    }
}