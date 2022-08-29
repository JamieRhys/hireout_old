package com.jre.hireout.services.contacts

import com.jre.hireout.database.entities.contacts.Contact
import com.jre.hireout.database.repository.contacts.ContactRepository
import com.jre.hireout.utils.log.logger
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
open class ContactServiceImpl(
    private val contactRepository: ContactRepository
) : ContactService {
    private val log = logger<ContactServiceImpl>()

    override fun saveContact(contact: Contact): Contact {
        log.info("Saving contact ${contact.contactName} to database.")
        return contactRepository.save(contact)
    }

    override fun getContact(contactName: String): Contact {
        log.info("Fetching contact $contactName from database.")
        return contactRepository.findByContactName(contactName)
    }

    override fun getContacts(): List<Contact> {
        log.info("Fetching all contacts from database.")
        return contactRepository.findAll()
    }


}