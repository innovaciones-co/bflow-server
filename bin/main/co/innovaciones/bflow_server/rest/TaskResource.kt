package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.TaskWriteDTO
import co.innovaciones.bflow_server.model.TaskReadDTO
import co.innovaciones.bflow_server.service.TaskService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.messaging.simp.SimpMessagingTemplate
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
    private val taskService: TaskService, private val messagingTemplate: SimpMessagingTemplate
) {
    private val logger: Logger = LoggerFactory.getLogger(TaskResource::class.java)

    @GetMapping
    fun getAllTasks(@RequestParam(name = "job_id") jobId: Long?): ResponseEntity<List<TaskReadDTO>> {
        logger.info("GET /api/tasks called with jobId: {}", jobId)
        return if (jobId != null) {
            ResponseEntity.ok(taskService.findAllByJob(jobId))
        } else {
            ResponseEntity.ok(taskService.findAll())
        }
    }

    @GetMapping("/{id}")
    fun getTask(@PathVariable(name = "id") id: Long): ResponseEntity<TaskReadDTO> {
        logger.info("GET /api/tasks/{} called", id)
        return ResponseEntity.ok(taskService.get(id))
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createTask(@RequestBody @Valid taskDTO: TaskWriteDTO): ResponseEntity<Long> {
        logger.info("POST /api/tasks called with taskDTO: {}", taskDTO)
        val createdId = taskService.create(taskDTO)
        logger.info("Task created with id: {}", createdId)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateTask(
        @PathVariable(name = "id") id: Long, @RequestBody @Valid taskDTO: TaskWriteDTO
    ): ResponseEntity<Long> {
        logger.info("PUT /api/tasks/{} called with taskDTO: {}", id, taskDTO)
        taskService.update(id, taskDTO)
        messagingTemplate.convertAndSend("/topic/tasks", taskService.get(id))
        logger.info("Task with id: {} updated", id)
        return ResponseEntity.ok(id)
    }

    @PutMapping
    fun updateTasks(
        @RequestBody @Valid tasksDTO: List<TaskWriteDTO>
    ): ResponseEntity<List<Long>> {
        logger.info("PUT /api/tasks called with tasksDTO: {}", tasksDTO)
        val updatedTasks = taskService.update(tasksDTO)
        updatedTasks.map { messagingTemplate.convertAndSend("/topic/tasks", it) }
        return ResponseEntity.ok(updatedTasks.map { it.id!! })
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteTask(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        logger.info("DELETE /api/tasks/{} called", id)
        taskService.delete(id)
        logger.info("Task with id: {} deleted", id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/send")
    @ApiResponse(responseCode = "204")
    fun taskNotification(@RequestBody tasks: List<Long>): ResponseEntity<String> {
        logger.info("POST /api/tasks/send called with tasks: {}", tasks)
        taskService.taskNotifyEmail(tasks)
        logger.info("Notification sent for tasks: {}", tasks)
        return ResponseEntity.noContent().build()
    }
}
