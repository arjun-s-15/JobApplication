package arjunSingh.JobApplication.Job;

import java.util.List;

public interface JobService {
    List<Job> findAll();

    void createJob(Job job);


    Job findById(Long id);

    boolean deleteById(Long id);

    boolean updateJob(Long id, Job updateJob);
}
