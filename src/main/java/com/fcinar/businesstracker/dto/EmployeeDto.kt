package com.fcinar.businesstracker.dto

import com.fcinar.businesstracker.model.Company
import com.fcinar.businesstracker.model.Department
import java.util.*

data class EmployeeDto(
    val id: UUID,
    val name: String,
    val surname: String,
    val company: Company,
    val department: Department
)
