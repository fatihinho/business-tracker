package com.fcinar.businesstracker.dto

import java.util.*

data class UpdateEmployeeRequest(
    val name: String,
    val surname: String,
    val companyId: UUID,
    val departmentId: UUID
)