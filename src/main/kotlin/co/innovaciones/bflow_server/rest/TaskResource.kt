package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.TaskCreateUpdateDTO
import co.innovaciones.bflow_server.model.TaskReadDTO
import co.innovaciones.bflow_server.service.TaskService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
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
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(
    value = ["/api/tasks"], produces = [MediaType.APPLICATION_JSON_VALUE]
)
@SecurityRequirement(name = "bearer-jwt")
class TaskResource(
    private val taskService: TaskService
) {

    @GetMapping
    fun getAllTasks(@RequestParam(name = "job_id") jobId: Long?): ResponseEntity<List<TaskReadDTO>> {
        if (jobId != null) {
            return ResponseEntity.ok(taskService.findAllByJob(jobId))
        }

        return ResponseEntity.ok(taskService.findAll())
    }

    @GetMapping("/{id}")
    fun getTask(@PathVariable(name = "id") id: Long): ResponseEntity<TaskReadDTO> = ResponseEntity.ok(taskService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createTask(@RequestBody @Valid taskDTO: TaskCreateUpdateDTO): ResponseEntity<Long> {
        val createdId = taskService.create(taskDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateTask(@PathVariable(name = "id") id: Long, @RequestBody @Valid taskDTO: TaskCreateUpdateDTO): ResponseEntity<TaskReadDTO> {
        taskService.update(id, taskDTO)
        return ResponseEntity.ok(taskService.get(id))
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteTask(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        taskService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
