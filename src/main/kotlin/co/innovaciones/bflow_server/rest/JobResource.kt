package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.JobReadDTO
import co.innovaciones.bflow_server.model.JobWriteDTO
import co.innovaciones.bflow_server.service.JobService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["/api/jobs"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@SecurityRequirement(name = "bearer-jwt")
class JobResource(
    private val jobService: JobService
) {
    private val logger: Logger = LoggerFactory.getLogger(JobResource::class.java)

    @GetMapping
    fun getAllJobs(): ResponseEntity<List<JobReadDTO>> {
        logger.info("GET /api/jobs called")
        val jobs = jobService.findAll()
        logger.info("Retrieved {} jobs", jobs.size)
        return ResponseEntity.ok(jobs)
    }

    @GetMapping("/{id}")
    fun getJob(@PathVariable(name = "id") id: Long): ResponseEntity<JobReadDTO> {
        logger.info("GET /api/jobs/{} called", id)
        val job = jobService.get(id)
        logger.info("Retrieved job: {}", job)
        return ResponseEntity.ok(job)
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createJob(@RequestBody @Valid jobDTO: JobWriteDTO): ResponseEntity<Long> {
        logger.info("POST /api/jobs called with jobDTO: {}", jobDTO)
        val createdId = jobService.create(jobDTO)
        logger.info("Job created with id: {}", createdId)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateJob(@PathVariable(name = "id") id: Long, @RequestBody @Valid jobDTO: JobWriteDTO): ResponseEntity<Long> {
        logger.info("PUT /api/jobs/{} called with jobDTO: {}", id, jobDTO)
        jobService.update(id, jobDTO)
        logger.info("Job with id: {} updated", id)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteJob(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        logger.info("DELETE /api/jobs/{} called", id)
        jobService.delete(id)
        logger.info("Job with id: {} deleted", id)
        return ResponseEntity.noContent().build()
    }
}
