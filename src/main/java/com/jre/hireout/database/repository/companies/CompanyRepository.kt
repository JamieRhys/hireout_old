package com.jre.hireout.database.repository.companies

import com.jre.hireout.database.entities.companies.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : JpaRepository<Company, Long> {
    fun findCompanyByCompanyName(companyName: String): Company
}