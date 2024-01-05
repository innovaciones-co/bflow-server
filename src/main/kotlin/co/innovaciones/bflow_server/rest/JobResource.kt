package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.JobDTO
import co.innovaciones.bflow_server.service.JobService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import java.lang.Void
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(
    value = ["/api/jobs"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class JobResource(
    private val jobService: JobService
) {

    @GetMapping
    fun getAllJobs(): ResponseEntity<List<JobDTO>> = ResponseEntity.ok(jobService.findAll())

    @GetMapping("/{id}")
    fun getJob(@PathVariable(name = "id") id: Long): ResponseEntity<JobDTO> =
            ResponseEntity.ok(jobService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createJob(@RequestBody @Valid jobDTO: JobDTO): ResponseEntity<Long> {
        val createdId = jobService.create(jobDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateJob(@PathVariable(name = "id") id: Long, @RequestBody @Valid jobDTO: JobDTO):
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
