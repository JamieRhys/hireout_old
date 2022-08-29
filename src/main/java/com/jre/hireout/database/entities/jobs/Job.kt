package com.jre.hireout.database.entities.jobs

import com.jre.hireout.database.entities.users.User
import java.time.LocalDate
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import javax.persistence.*

@Entity
@Table(name = "table_jobs")
data class Job(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var owningUser: User? = null,

    @Column(name = "job_name", nullable = false)
    var jobName: String = "Default Name",

    @Column(name = "job_date_outgoing", nullable = false)
    var dateOutgoing: LocalDate = LocalDate.now(),

    @Column(name = "job_time_outgoing", nullable = false)
    var timeOutgoing: LocalTime = LocalTime.now().plusHours(1).truncatedTo(ChronoUnit.MINUTES),

    @Column(name = "job_time_start_outgoing_linked", nullable = false)
    var isJobOutgoingStartLinked: Boolean = true,

    @Column(name = "job_date_start")
    var dateStarting: LocalDate? = null,

    @Column(name = "job_time_start")
    var timeStarting: LocalTime? = null,

    @Column(name = "job_date_returning", nullable = false)
    var dateReturning: LocalDate = LocalDate.now(),

    @Column(name = "job_time_returning", nullable = false)
    var timeReturning: LocalTime = LocalTime.now().plusHours(1).truncatedTo(ChronoUnit.MINUTES),

    @Column(name = "job_date_finishing")
    var dateFinishing: LocalDate? = null,

    @Column(name = "job_time_finishing")
    var timeFinishing: LocalTime? = null,

    @Column(name = "job_charge_period_days")
    var chargePeriodDays: Int = 0,

    @Column(name = "job_charge_period_hours")
    var chargePeriodHours: Int = 0,

    @Column(name = "job_note")
    var jobNote: String? = null,

    @Column(name = "job_client_reference")
    var clientReference: String? = null,

    @Column(name = "job_default_discount")
    var defaultDiscount: Float = 0.0f,

    @Column(name = "job_calculate_late_fees", nullable = false)
    var calculateLateFees: Boolean = false,

    @Column(name = "job_allow_early_returns", nullable = false)
    var allowEarlyReturns: Boolean = false,
)
