package arjunSingh.JobApplication.Job;

import arjunSingh.JobApplication.Company.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/jobs")

public class JobController {
    private List<Job> jobs = new ArrayList<>();
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
        System.out.println("GET /jobs called");
        return ResponseEntity.ok( jobService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully",HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Job> findById(@PathVariable Long id) {

        Job job = jobService.findById(id);
        return (job != null)
                ? ResponseEntity.ok(job)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        boolean deleted = jobService.deleteById(id);
        if(deleted){
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putById(@PathVariable Long id,@RequestBody Job updatejob){
            boolean isUpdated = jobService.updateJob(id,updatejob);

            if (isUpdated){
                return ResponseEntity.ok("Job updated successfully");
            }else{
                return ResponseEntity.notFound().build();
            }
    }


}


