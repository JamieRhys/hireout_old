package com.jre.hireout.services.companies

import com.jre.hireout.database.entities.companies.Company
import com.jre.hireout.database.repository.companies.CompanyRepository
import com.jre.hireout.utils.log.logger
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
open class CompanyServiceImpl(
    private val companyRepository: CompanyRepository
) : CompanyService  {
    private val log = logger<CompanyServiceImpl>()

    override fun saveCompany(company: Company): Company {
        log.info("Saving company ${company.companyName} to database.")
        return companyRepository.save(company)
    }

    override fun getCompany(companyName: String): Company {
        log.info("Fetching company $companyName from database.")
        return companyRepository.findCompanyByCompanyName(companyName)
    }

    override fun getCompanies(): List<Company> {
        log.info("Fetching all jobs from database.")
        return companyRepository.findAll()
    }

}