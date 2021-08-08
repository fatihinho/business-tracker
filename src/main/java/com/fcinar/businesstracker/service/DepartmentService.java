package com.fcinar.businesstracker.service;

import com.fcinar.businesstracker.dto.CreateDepartmentRequest;
import com.fcinar.businesstracker.dto.DepartmentDto;
import com.fcinar.businesstracker.dto.UpdateDepartmentRequest;
import com.fcinar.businesstracker.dto.converter.DepartmentDtoConverter;
import com.fcinar.businesstracker.exception.DepartmentNotFoundException;
import com.fcinar.businesstracker.model.Company;
import com.fcinar.businesstracker.model.Department;
import com.fcinar.businesstracker.repository.IDepartmentRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final IDepartmentRepository departmentRepository;
    private final DepartmentDtoConverter departmentDtoConverter;
    private final CompanyService companyService;

    public DepartmentService(IDepartmentRepository departmentRepository,
                             DepartmentDtoConverter departmentDtoConverter,
                             CompanyService companyService) {
        this.departmentRepository = departmentRepository;
        this.departmentDtoConverter = departmentDtoConverter;
        this.companyService = companyService;
    }

    protected Department findDepartmentById(UUID id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department could not found by id: " + id));
    }

    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(departmentDtoConverter::convert).collect(Collectors.toList());
    }

    public DepartmentDto getDepartmentById(UUID id) {
        return departmentDtoConverter.convert(findDepartmentById(id));
    }

    public List<DepartmentDto> getAllDepartmentsByCompanyId(UUID companyId) {
        List<Department> departments = departmentRepository.findAllByCompanyId(companyId);
        return departments.stream().map(departmentDtoConverter::convert).collect(Collectors.toList());
    }

    public List<DepartmentDto> getAllDepartmentsByEmployeeSizeLessThanEqual(int employeeSize) {
        List<Department> departments = departmentRepository.findAllByEmployeeSizeLessThanEqual(employeeSize);
        return departments.stream().map(departmentDtoConverter::convert).collect(Collectors.toList());
    }

    public List<DepartmentDto> getAllDepartmentsByEmployeeSizeGreaterThanEqual(int employeeSize) {
        List<Department> departments = departmentRepository.findAllByEmployeeSizeGreaterThanEqual(employeeSize);
        return departments.stream().map(departmentDtoConverter::convert).collect(Collectors.toList());
    }

    public DepartmentDto createDepartment(@NotNull CreateDepartmentRequest createDepartmentRequest) {
        Company company = companyService.findCompanyById(createDepartmentRequest.getCompanyId());
        Department department = new Department(createDepartmentRequest.getName(),
                createDepartmentRequest.getEmployeeSize(), company);
        return departmentDtoConverter.convert(departmentRepository.save(department));
    }

    public DepartmentDto updateDepartmentById(UUID id, @NotNull UpdateDepartmentRequest updateDepartmentRequest) {
        Department department = findDepartmentById(id);
        department.setName(updateDepartmentRequest.getName());
        department.setEmployeeSize(updateDepartmentRequest.getEmployeeSize());
        return departmentDtoConverter.convert(departmentRepository.save(department));
    }

    public void deleteAllDepartments() {
        departmentRepository.deleteAll();
    }

    public void deleteDepartmentById(UUID id) {
        departmentRepository.deleteById(id);
    }
}
