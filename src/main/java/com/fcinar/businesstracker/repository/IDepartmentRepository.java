package com.fcinar.businesstracker.repository;

import com.fcinar.businesstracker.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, UUID> {
    List<Department> findAllByCompanyId(UUID companyId);

    List<Department> findAllByEmployeeSizeLessThanEqual(int employeeSize);

    List<Department> findAllByEmployeeSizeGreaterThanEqual(int employeeSize);
}
