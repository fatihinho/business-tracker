package com.fcinar.businesstracker.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Company")
data class Company(
    @Id
    @Column(name = "Id")
    val id: UUID,

    @Column(name = "Name")
    var name: String,

    @Column(name = "Email")
    var email: String,

    @Column(name = "Country")
    var country: String?,

    @Column(name = "Address")
    var address: String?
) {
    constructor(name: String, email: String, country: String?, address: String?) : this(
        id = UUID.randomUUID(),
        name = name,
        email = email,
        country = country,
        address = address
    )

    override fun toString(): String {
        return String.format(
            "Name: $name\nEmail: $email\nCountry: $country\nAddress: $address"
        )
    }
}
