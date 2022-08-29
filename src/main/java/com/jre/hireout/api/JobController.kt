package com.jre.hireout.api

import com.jre.hireout.database.entities.jobs.Job
import com.jre.hireout.services.jobs.JobService
import com.jre.hireout.services.users.UserService
import com.jre.hireout.utils.log.logger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/api")
class JobController(
    private val jobService: JobService,
    private val userService: UserService,
) {
    private val log = logger<JobController>()

    @GetMapping("/jobs")
    fun getJobs(): ResponseEntity<List<Job>> {
        return ResponseEntity.ok().body(jobService.getJobs());
    }

    @PostMapping("/job/save")
    fun saveJob(@RequestBody jobForm: JobForm): ResponseEntity<Job> {
        jobForm.job.owningUser = userService.getUser(jobForm.username)

        if(jobForm.job.isJobOutgoingStartLinked) {
            jobForm.job.dateStarting = jobForm.job.dateOutgoing
            jobForm.job.timeStarting = jobForm.job.timeOutgoing

            jobForm.job.dateFinishing = jobForm.job.dateReturning
            jobForm.job.timeFinishing = jobForm.job.timeReturning
        }

        val uri: URI = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString())
        return ResponseEntity.created(uri).body(jobService.saveJob(jobForm.job))
    }

    data class JobForm(
        val job: Job,
        val username: String,
    )
}