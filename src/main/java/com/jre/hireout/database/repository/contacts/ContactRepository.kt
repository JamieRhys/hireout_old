package com.jre.hireout.database.repository.contacts

import com.jre.hireout.database.entities.contacts.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactRepository : JpaRepository<Contact, Long> {
    fun findByContactName(contactName: String): Contact
}