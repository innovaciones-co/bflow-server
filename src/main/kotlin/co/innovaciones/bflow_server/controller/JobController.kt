package co.innovaciones.bflow_server.controller

import co.innovaciones.bflow_server.model.JobReportDTO
import co.innovaciones.bflow_server.rest.JobResource
import co.innovaciones.bflow_server.service.JobService
import co.innovaciones.bflow_server.service.TaskService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@Controller
@RequestMapping("/jobs")
class JobController(
    private val jobService: JobService,
    private val taskService: TaskService,
    private val pdfJobReport: PdfJobReport
) {
    private val logger: Logger = LoggerFactory.getLogger(JobResource::class.java)

    @GetMapping
    fun jobsReport(model: Model): String {
        logger.info("GET /jobs called")
        val jobs = jobService.findAll()
        logger.info("Retrieved {} jobs", jobs.size)

        val jobsGroupedBySupervisor = jobs.groupBy { it.supervisor?.id }
        val reportDate = OffsetDateTime.now()

        val jobReports = jobsGroupedBySupervisor.map { entry ->
            val supervisorJobs = entry.value.map { job ->
                val nextTask = taskService.findNextTaskByJob(job.id!!)
                val overdueTasks = taskService.findOverdueTasksByJob(job.id!!, reportDate)
                mapOf(
                    "job" to job, "nextTask" to nextTask, "overdueTasks" to overdueTasks
                )
            }
            entry.key to supervisorJobs
        }.toMap()

        model.addAttribute("jobReports", jobReports)
        model.addAttribute("reportDate", reportDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))

        return "jobs/list"
    }

    @GetMapping("/report")
    fun generateReport(@RequestParam reportType: String): ResponseEntity<ByteArrayResource> {
        logger.info("Generating report of type: $reportType")
        val jobs = jobService.findAll()
        val reportDate = OffsetDateTime.now()
        val jobsGroupedBySupervisor = jobs.groupBy { it.supervisor?.id }

        val jobReports = jobsGroupedBySupervisor.map { entry ->
            val supervisorJobs = entry.value.map { job ->
                val nextTask = taskService.findNextTaskByJob(job.id!!)
                val overdueTasks = taskService.findOverdueTasksByJob(job.id!!, reportDate)
                JobReportDTO(job, nextTask, overdueTasks)            }
            entry.key!! to supervisorJobs
        }.toMap()

        val report: ByteArray = when (reportType.lowercase(Locale.getDefault())) {
            //"csv" -> csvJobReport.generateReport(jobReports, reportDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            "pdf" -> pdfJobReport.generateReport(jobReports, reportDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            else -> throw IllegalArgumentException("Unsupported report type: $reportType")
        }

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=report.$reportType")
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(ByteArrayResource(report))
    }
}