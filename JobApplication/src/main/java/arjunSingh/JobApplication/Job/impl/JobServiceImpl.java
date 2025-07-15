package arjunSingh.JobApplication.Job.impl;

import arjunSingh.JobApplication.Company.Company;
import arjunSingh.JobApplication.Company.CompanyRepository;
import arjunSingh.JobApplication.Job.Job;
import arjunSingh.JobApplication.Job.JobRepository;
import arjunSingh.JobApplication.Job.JobService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }




    private Long nextId = 1L;
    @Override
    public List<Job> findAll(){
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Long companyId, Job job) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found with id: " + companyId));

        job.setCompany(company);  // ✅ Setting company
        jobRepository.save(job);
    }


    @Override
    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);

    }

    @Override
    public boolean deleteById(Long id) {
        if(jobRepository.existsById(id)){
            jobRepository.deleteById(id);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean updateJob(Long id, Job updateJob){
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job job = jobOptional.get();
            job.setTitle(updateJob.getTitle());
            job.setDescription(updateJob.getDescription());
            job.setMaxSalary(updateJob.getMaxSalary());
            job.setMinSalary(updateJob.getMinSalary());
            job.setLocation(updateJob.getLocation());

            jobRepository.save(job);
            return true;
        }
        return false;
    }


}