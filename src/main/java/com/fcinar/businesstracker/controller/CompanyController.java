package com.fcinar.businesstracker.controller;

import com.fcinar.businesstracker.dto.CompanyDto;
import com.fcinar.businesstracker.dto.CreateCompanyRequest;
import com.fcinar.businesstracker.dto.UpdateCompanyRequest;
import com.fcinar.businesstracker.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/companies")
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<CompanyDto> companies = companyService.getAllCompanies();
        if (companies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        try {
            return new ResponseEntity<>(companies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable UUID id) {
        try {
            CompanyDto company = companyService.getCompanyById(id);
            return new ResponseEntity<>(company, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/companies")
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CreateCompanyRequest createCompanyRequest) {
        try {
            CompanyDto company = companyService.createCompany(createCompanyRequest);
            return new ResponseEntity<>(company, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<CompanyDto> updateCompanyById(@PathVariable UUID id,
                                                        @RequestBody UpdateCompanyRequest updateCompanyRequest) {
        try {
            CompanyDto company = companyService.updateCompanyById(id, updateCompanyRequest);
            return new ResponseEntity<>(company, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/companies")
    public ResponseEntity<HttpStatus> deleteAllCompanies() {
        try {
            companyService.deleteAllCompanies();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<HttpStatus> deleteCompanyById(@PathVariable UUID id) {
        try {
            companyService.deleteCompanyById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
