package arjunSingh.JobApplication.Job;

import arjunSingh.JobApplication.Company.Company;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Job {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String title;
     private String description;
     private String minSalary;
     private String maxSalary;
     private String location;

     @ManyToOne
     private Company company;
     public Job() {
     }
}



