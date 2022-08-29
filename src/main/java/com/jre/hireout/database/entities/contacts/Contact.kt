package com.jre.hireout.database.entities.contacts

import com.jre.hireout.database.entities.companies.Company
import javax.persistence.*

@Entity
@Table(name = "table_contacts")
data class Contact(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    val id: Long? = null,

    @Column(name = "contact_name", nullable = false)
    var contactName: String = "Joe Bloggs",

    @Column(name = "contact_department")
    var contactDepartment: String = "",

    @Column(name = "contact_job_description")
    var jobDescription: String = "",

    @Column(name = "contact_extension")
    var extension: String = "",

    @Column(name = "contact_mobile_number")
    var mobileNumber: String = "",

    @Column(name = "contact_email")
    var email: String = "",

    @Column(name = "contact_note")
    var contactNotes: String = "",

    @ManyToOne
    @JoinColumn(name = "company_id")
    var company: Company? = null,
)