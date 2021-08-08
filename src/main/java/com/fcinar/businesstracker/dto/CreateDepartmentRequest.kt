package com.fcinar.businesstracker.dto

import java.util.*

data class CreateDepartmentRequest(
    val name: String,
    val employeeSize: Int,
    val companyId: UUID
)
