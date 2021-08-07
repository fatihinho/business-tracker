package com.fcinar.businesstracker.dto.converter;

import com.fcinar.businesstracker.dto.DepartmentDto;
import com.fcinar.businesstracker.model.Department;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class DepartmentDtoConverter {
    public DepartmentDto convert(@NotNull Department from) {
        return new DepartmentDto(from.getId(), from.getName(), from.getEmployeeSize(), from.getCompany());
    }
}
