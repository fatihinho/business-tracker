package com.fcinar.businesstracker.service;

import com.fcinar.businesstracker.dto.CompanyDto;
import com.fcinar.businesstracker.dto.CreateCompanyRequest;
import com.fcinar.businesstracker.dto.UpdateCompanyRequest;
import com.fcinar.businesstracker.dto.converter.CompanyDtoConverter;
import com.fcinar.businesstracker.exception.CompanyNotFoundException;
import com.fcinar.businesstracker.model.Company;
import com.fcinar.businesstracker.repository.ICompanyRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final ICompanyRepository companyRepository;
    private final CompanyDtoConverter companyDtoConverter;

    public CompanyService(ICompanyRepository companyRepository, CompanyDtoConverter companyDtoConverter) {
        this.companyRepository = companyRepository;
        this.companyDtoConverter = companyDtoConverter;
    }

    private Company findCompanyById(UUID id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company could not found by id: " + id));
    }

    public List<CompanyDto> getAllCompanies() {
        return companyRepository.findAll().stream().map(companyDtoConverter::convert).collect(Collectors.toList());
    }

    public CompanyDto getCompanyById(UUID id) {
        Company company = findCompanyById(id);
        return companyDtoConverter.convert(company);
    }

    public CompanyDto createCompany(@NotNull CreateCompanyRequest createCompanyRequest) {
        Company company = new Company(createCompanyRequest.getName(), createCompanyRequest.getEmail(),
                createCompanyRequest.getCountry(), createCompanyRequest.getAddress());
        return companyDtoConverter.convert(companyRepository.save(company));
    }

    public CompanyDto updateCompanyById(UUID id, @NotNull UpdateCompanyRequest updateCompanyRequest) {
        Company company = findCompanyById(id);
        company.setName(updateCompanyRequest.getName());
        company.setEmail(updateCompanyRequest.getEmail());
        company.setCountry(updateCompanyRequest.getCountry());
        company.setAddress(updateCompanyRequest.getAddress());
        return companyDtoConverter.convert(companyRepository.save(company));
    }

    public void deleteAllCompanies() {
        companyRepository.deleteAll();
    }

    public void deleteCompanyById(UUID id) {
        companyRepository.deleteById(id);
    }
}
