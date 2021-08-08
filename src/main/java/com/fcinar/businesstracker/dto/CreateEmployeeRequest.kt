package com.fcinar.businesstracker.dto

import java.util.*

data class CreateEmployeeRequest(
    val name: String,
    val surname: String,
    val companyId: UUID,
    val departmentId: UUID
)
