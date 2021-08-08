package com.fcinar.businesstracker.service;

import com.fcinar.businesstracker.dto.*;
import com.fcinar.businesstracker.dto.converter.EmployeeDtoConverter;
import com.fcinar.businesstracker.exception.EmployeeNotFoundException;
import com.fcinar.businesstracker.model.Company;
import com.fcinar.businesstracker.model.Department;
import com.fcinar.businesstracker.model.Employee;
import com.fcinar.businesstracker.repository.IEmployeeRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final IEmployeeRepository employeeRepository;
    private final EmployeeDtoConverter employeeDtoConverter;
    private final CompanyService companyService;
    private final DepartmentService departmentService;

    public EmployeeService(IEmployeeRepository employeeRepository, EmployeeDtoConverter employeeDtoConverter, CompanyService companyService, DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.employeeDtoConverter = employeeDtoConverter;
        this.companyService = companyService;
        this.departmentService = departmentService;
    }

    protected Employee findEmployeeById(UUID id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee could not found by id: " + id));
    }

    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream().map(employeeDtoConverter::convert).collect(Collectors.toList());
    }

    public EmployeeDto getEmployeeById(UUID id) {
        Employee employee = findEmployeeById(id);
        return employeeDtoConverter.convert(employee);
    }

    public EmployeeDto createEmployee(@NotNull CreateEmployeeRequest createEmployeeRequest) {
        Company company = companyService.findCompanyById(createEmployeeRequest.getCompanyId());
        Department department = departmentService.findDepartmentById(createEmployeeRequest.getDepartmentId());
        Employee employee = new Employee(createEmployeeRequest.getName(),
                createEmployeeRequest.getSurname(), company, department);
        return employeeDtoConverter.convert(employeeRepository.save(employee));
    }

    public EmployeeDto updateEmployeeById(UUID id, @NotNull UpdateEmployeeRequest updateEmployeeRequest) {
        Company company = companyService.findCompanyById(updateEmployeeRequest.getCompanyId());
        Department department = departmentService.findDepartmentById(updateEmployeeRequest.getDepartmentId());
        Employee employee = findEmployeeById(id);
        employee.setName(updateEmployeeRequest.getName());
        employee.setSurname(updateEmployeeRequest.getSurname());
        employee.setCompany(company);
        employee.setDepartment(department);
        return employeeDtoConverter.convert(employeeRepository.save(employee));
    }

    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }

    public void deleteEmployeeById(UUID id) {
        employeeRepository.deleteById(id);
    }
}
