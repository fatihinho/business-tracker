package com.fcinar.businesstracker.dto.converter;

import com.fcinar.businesstracker.dto.CompanyDto;
import com.fcinar.businesstracker.model.Company;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class CompanyDtoConverter {
    public CompanyDto convert(@NotNull Company from) {
        return new CompanyDto(from.getId(), from.getName(), from.getEmail(), from.getCountry(), from.getAddress());
    }
}
