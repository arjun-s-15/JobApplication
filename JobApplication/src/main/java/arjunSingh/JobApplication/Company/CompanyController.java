package arjunSingh.JobApplication.Company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")

public class CompanyController {

        private final CompanyService companyService;

        public CompanyController(CompanyService companyService){
            this.companyService = companyService;
        }
        @GetMapping
        public List<Company> getAllCompanies(){
            return companyService.getAllCompanies();
        }

        @GetMapping("/{id}")
        public Optional<Company> findById(@PathVariable Long id){
            return companyService.findById(id);
        }

        @PutMapping("/{id}")
        public ResponseEntity<String> updateCompany(@PathVariable Long id,@RequestBody Company company){
            boolean updated = companyService.updateCompany(id, company);
            if(updated){
                return ResponseEntity.ok("Company Updated Successfully");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("company not found");
            }
        }

        @PostMapping
        public ResponseEntity<String> addCompany(@RequestBody Company company){
            companyService.createCompany(company);
            return new ResponseEntity<>("Company added Successfully",HttpStatus.CREATED);
        }
//4:45:02
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteCompany(@PathVariable Long id){
            boolean isDeleted = companyService.deleteById(id);
            if(isDeleted){
                return new ResponseEntity<>("Company Deleted Successfully", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Company Not Found",HttpStatus.NOT_FOUND);
            }
        }

}
