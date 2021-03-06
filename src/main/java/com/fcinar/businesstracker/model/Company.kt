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

    @Column(name = "Name", length = 30)
    var name: String,

    @Column(name = "Email", length = 20)
    var email: String,

    @Column(name = "Country", length = 20)
    var country: String?,

    @Column(name = "Address", length = 100)
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
