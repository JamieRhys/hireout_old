package com.jre.hireout.services.companies

import com.jre.hireout.database.entities.companies.Company

interface CompanyService {
    fun saveCompany(company: Company): Company

    fun getCompany(companyName: String): Company

    fun getCompanies(): List<Company>
}