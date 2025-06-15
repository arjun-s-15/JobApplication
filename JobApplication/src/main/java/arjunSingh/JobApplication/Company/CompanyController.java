package arjunSingh.JobApplication.Company;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

        @PutMapping
        public ResponseEntity<String> updateCompany(@PathVariable Long id,@RequestBody Company company){
            boolean updated = companyService.updateCompany(id, company);
            if(updated){
                return ResponseEntity.ok("Company Updated Successfully");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("company not found");
            }
        }



}
