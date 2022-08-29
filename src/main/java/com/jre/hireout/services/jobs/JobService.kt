package com.jre.hireout.services.jobs

import com.jre.hireout.database.entities.jobs.Job

interface JobService {
    fun saveJob(job: Job): Job

    fun getJob(jobName: String): Job

    fun getJobs(): List<Job>
}