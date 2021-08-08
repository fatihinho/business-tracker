package com.fcinar.businesstracker.controller;

import com.fcinar.businesstracker.dto.CreateDepartmentRequest;
import com.fcinar.businesstracker.dto.DepartmentDto;
import com.fcinar.businesstracker.dto.UpdateDepartmentRequest;
import com.fcinar.businesstracker.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        try {
            List<DepartmentDto> departments = departmentService.getAllDepartments();
            if (departments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable UUID id) {
        try {
            DepartmentDto department = departmentService.getDepartmentById(id);
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/departments", params = "companyId")
    public ResponseEntity<List<DepartmentDto>> getAllDepartmentsByCompanyId(@RequestParam UUID companyId) {
        try {
            List<DepartmentDto> departments = departmentService.getAllDepartmentsByCompanyId(companyId);
            if (departments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/departments", params = "lessThanEqual")
    public ResponseEntity<List<DepartmentDto>> getAllDepartmentsByEmployeeSizeLessThanEqual(
            @RequestParam int lessThanEqual) {
        try {
            List<DepartmentDto> departments = departmentService
                    .getAllDepartmentsByEmployeeSizeLessThanEqual(lessThanEqual);
            if (departments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/departments", params = "greaterThanEqual")
    public ResponseEntity<List<DepartmentDto>> getAllDepartmentsByEmployeeSizeGreaterThanEqual(
            @RequestParam int greaterThanEqual) {
        try {
            List<DepartmentDto> departments = departmentService
                    .getAllDepartmentsByEmployeeSizeGreaterThanEqual(greaterThanEqual);
            if (departments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/departments")
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody CreateDepartmentRequest createDepartmentRequest) {
        try {
            DepartmentDto department = departmentService.createDepartment(createDepartmentRequest);
            return new ResponseEntity<>(department, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<DepartmentDto> updateDepartmentById(@PathVariable UUID id,
                                                              @RequestBody UpdateDepartmentRequest updateDepartmentRequest) {
        try {
            DepartmentDto department = departmentService.updateDepartmentById(id, updateDepartmentRequest);
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/departments")
    public ResponseEntity<HttpStatus> deleteAllDepartments() {
        try {
            departmentService.deleteAllDepartments();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<HttpStatus> deleteDepartmentById(@PathVariable UUID id) {
        try {
            departmentService.deleteDepartmentById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
