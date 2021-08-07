package com.fcinar.businesstracker.dto

import java.util.*

data class CompanyDto(
    val id: UUID,
    val name: String,
    val email: String,
    val country: String?,
    val address: String?
)
