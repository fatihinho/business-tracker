package com.fcinar.businesstracker.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Department")
data class Department(
    @Id
    @Column(name = "Id")
    val id: UUID,

    @Column(name = "Name")
    val name: String,

    @Column(name = "EmployeeSize")
    val employeeSize: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CompanyId")
    val company: Company
) {
    constructor(name: String, employeeSize: Int, company: Company) : this(
        id = UUID.randomUUID(),
        name = name,
        employeeSize = employeeSize,
        company = company
    )

    override fun toString(): String {
        return String.format(
            "Name: $name\nEmployee Size: $employeeSize\nCompany Name: ${company.name}"
        )
    }
}
