package com.fcinar.businesstracker.dto

import com.fcinar.businesstracker.model.Company
import java.util.*

data class DepartmentDto(
    val id: UUID,
    val name: String,
    val employeeSize: Int,
    val company: Company
)
