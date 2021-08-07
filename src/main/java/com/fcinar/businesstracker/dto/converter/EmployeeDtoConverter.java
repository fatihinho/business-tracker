package com.fcinar.businesstracker.dto.converter;

import com.fcinar.businesstracker.dto.EmployeeDto;
import com.fcinar.businesstracker.model.Employee;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDtoConverter {
    public EmployeeDto convert(@NotNull Employee from) {
        return new EmployeeDto(from.getId(), from.getName(), from.getSurname(), from.getCompany(), from.getDepartment());
    }
}
