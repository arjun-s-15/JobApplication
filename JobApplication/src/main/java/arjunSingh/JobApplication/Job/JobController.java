package arjunSingh.JobApplication.Job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping("/companies/{companyId}")
    public ResponseEntity<String> createJob(@PathVariable Long companyId, @RequestBody Job job) {
        jobService.createJob(companyId, job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findById(@PathVariable Long id) {
        Job job = jobService.findById(id);
        return (job != null)
                ? ResponseEntity.ok(job)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        boolean updated = jobService.updateJob(id, updatedJob);
        return updated
                ? ResponseEntity.ok("Job updated successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        boolean deleted = jobService.deleteById(id);
        return deleted
                ? ResponseEntity.ok("Job deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
    }
}
