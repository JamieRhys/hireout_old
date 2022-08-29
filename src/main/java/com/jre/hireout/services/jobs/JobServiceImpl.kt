package com.jre.hireout.services.jobs

import com.jre.hireout.database.entities.jobs.Job
import com.jre.hireout.database.repository.jobs.JobRepository
import com.jre.hireout.utils.log.logger
import org.slf4j.Logger
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
open class JobServiceImpl(
    private val jobRepository: JobRepository
) : JobService {
    private val log: Logger = logger<JobServiceImpl>()

    override fun saveJob(job: Job): Job {
        log.info("Saving job ${job.jobName} to database.")
        return jobRepository.save(job)
    }

    override fun getJob(jobName: String): Job {
        log.info("Fetching job $jobName from database.")
        return jobRepository.findByJobName(jobName)
    }

    override fun getJobs(): List<Job> {
        log.info("Fetching all jobs from database.")
        return jobRepository.findAll()
    }
}