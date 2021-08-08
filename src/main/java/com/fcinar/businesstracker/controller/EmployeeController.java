package com.fcinar.businesstracker.controller;

import com.fcinar.businesstracker.dto.*;
import com.fcinar.businesstracker.service.CompanyService;
import com.fcinar.businesstracker.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        try {
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable UUID id) {
        try {
            EmployeeDto employee = employeeService.getEmployeeById(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
        try {
            EmployeeDto employee = employeeService.createEmployee(createEmployeeRequest);
            return new ResponseEntity<>(employee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@PathVariable UUID id,
                                                          @RequestBody UpdateEmployeeRequest updateEmployeeRequest) {
        try {
            EmployeeDto employee = employeeService.updateEmployeeById(id, updateEmployeeRequest);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employees")
    public ResponseEntity<HttpStatus> deleteAllEmployees() {
        try {
            employeeService.deleteAllEmployees();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable UUID id) {
        try {
            employeeService.deleteEmployeeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}