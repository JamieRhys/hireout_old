package com.jre.hireout.database.repository.jobs

import com.jre.hireout.database.entities.jobs.Job
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JobRepository : JpaRepository<Job, Long> {
    fun findByJobName(jobName: String): Job
}