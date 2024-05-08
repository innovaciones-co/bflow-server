package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.JobDTO
import co.innovaciones.bflow_server.model.JobReadDTO
import co.innovaciones.bflow_server.model.JobWriteDTO
import co.innovaciones.bflow_server.service.JobService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
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

    @GetMapping
    fun getAllJobs(): ResponseEntity<List<JobReadDTO>> = ResponseEntity.ok(jobService.findAll())

    @GetMapping("/{id}")
    fun getJob(@PathVariable(name = "id") id: Long): ResponseEntity<JobReadDTO> =
            ResponseEntity.ok(jobService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createJob(@RequestBody @Valid jobDTO: JobWriteDTO): ResponseEntity<Long> {
        val createdId = jobService.create(jobDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateJob(@PathVariable(name = "id") id: Long, @RequestBody @Valid jobDTO: JobWriteDTO):
            ResponseEntity<Long> {
        jobService.update(id, jobDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteJob(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        jobService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
