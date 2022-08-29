package com.jre.hireout.api

import com.jre.hireout.database.entities.contacts.Contact
import com.jre.hireout.services.companies.CompanyService
import com.jre.hireout.services.contacts.ContactService
import com.jre.hireout.utils.log.logger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/api")
class ContactController(
    private val contactService: ContactService,
    private val companyService: CompanyService
) {
    private val log = logger<ContactController>()

    @GetMapping("/contacts")
    fun getContacts(): ResponseEntity<List<Contact>> {
        return ResponseEntity.ok().body(contactService.getContacts())
    }

    @PostMapping("/contact/save")
    fun saveContact(@RequestBody contactForm: CompanyContactForm): ResponseEntity<Contact> {
        contactForm.contact.company = companyService.getCompany(contactForm.companyName)

        val uri: URI = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/contact/save").toUriString())
        return ResponseEntity.created(uri).body(contactService.saveContact(contactForm.contact))
    }

    data class CompanyContactForm(
        var companyName: String,
        var contact: Contact,
    )
}