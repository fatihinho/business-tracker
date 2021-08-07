package com.fcinar.businesstracker.dto

data class CreateCompanyRequest(
    val name: String,
    val email: String,
    val country: String?,
    val address: String?
)
