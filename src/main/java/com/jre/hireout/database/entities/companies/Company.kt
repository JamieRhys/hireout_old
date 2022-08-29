package com.jre.hireout.database.entities.companies

import com.fasterxml.jackson.annotation.JsonIgnore
import com.jre.hireout.database.entities.contacts.Contact
import javax.persistence.*

@Entity
@Table(name = "table_companies")
data class Company(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    val id: Long? = null,

    @Column(name = "company_name", nullable = false)
    var companyName: String = "ACME Company",

    @Column(name = "company_address")
    var companyAddress: String = "",

    @Column(name = "company_postcode")
    var companyPostcode: String = "",

    @Column(name = "company_telephone")
    var companyTelephone: String = "",

    @Column(name = "company_fax")
    var companyFax: String = "",

    @Column(name = "company_vat_number")
    var companyVatNumber: String = "",

    @Column(name = "company_website")
    var companyWebsite: String = "",

    @Column(name = "company_notes")
    var companyNotes: String = "",

    @OneToMany(mappedBy = "company")
    @JsonIgnore
    var contacts: List<Contact> = ArrayList(),
)
