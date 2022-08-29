package com.jre.hireout.services.contacts

import com.jre.hireout.database.entities.contacts.Contact

interface ContactService {
    fun saveContact(contact: Contact): Contact

    fun getContact(contactName: String): Contact

    fun getContacts(): List<Contact>
}