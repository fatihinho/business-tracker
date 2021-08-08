package com.fcinar.businesstracker.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Employee")
data class Employee(
    @Id
    @Column(name = "Id")
    val id: UUID,

    @Column(name = "Name", length = 30)
    var name: String,

    @Column(name = "Surname", length = 30)
    var surname: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CompanyId")
    var company: Company,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DepartmentId")
    var department: Department
) {
    constructor(name: String, surname: String, company: Company, department: Department) : this(
        id = UUID.randomUUID(),
        name = name,
        surname = surname,
        company = company,
        department = department
    )

    override fun toString(): String {
        return String.format(
            "Name: $name\nSurname: $surname\nCompany Name: ${company.name}\nDepartment Name: ${department.name}"
        )
    }
}
