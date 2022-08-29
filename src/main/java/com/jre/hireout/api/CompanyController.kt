package com.jre.hireout.api

import com.jre.hireout.database.entities.companies.Company
import com.jre.hireout.services.companies.CompanyService
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
class CompanyController(
    private val companyService: CompanyService
) {
    val log = logger<CompanyController>()

    @GetMapping("/companies")
    fun getCompanies(): ResponseEntity<List<Company>> {
        return ResponseEntity.ok().body(companyService.getCompanies())
    }

    @PostMapping("/company/save")
    fun saveCompany(@RequestBody company: Company): ResponseEntity<Company> {
        val uri: URI = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/company/save").toUriString())
        return ResponseEntity.created(uri).body(companyService.saveCompany(company))
    }
}