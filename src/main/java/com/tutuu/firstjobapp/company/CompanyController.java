package com.tutuu.firstjobapp.company;

import com.tutuu.firstjobapp.job.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> findAll() {
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        companyService.createCompany(company);
        return new ResponseEntity<>("company created", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        boolean deleted = companyService.deleteCompanyById(id);
        if (deleted) {
            return new ResponseEntity<>("company deletede successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany (@PathVariable Long id,
                                                 @RequestBody Company updatedCompany){
        boolean updated = companyService.updateCompany(id,updatedCompany);
        if (updated){
            return  new ResponseEntity<>("company updated successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
