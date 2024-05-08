package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.TaskWriteDTO
import co.innovaciones.bflow_server.model.TaskReadDTO
import co.innovaciones.bflow_server.service.EmailService
import co.innovaciones.bflow_server.service.TaskService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import java.lang.Void
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
import sibModel.TaskList


@RestController
@RequestMapping(
    value = ["/api/tasks"], produces = [MediaType.APPLICATION_JSON_VALUE]
)
@SecurityRequirement(name = "bearer-jwt")
class TaskResource(
    private val taskService: TaskService,
    private val emailService: EmailService,
    private val messagingTemplate: SimpMessagingTemplate
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
    fun createTask(@RequestBody @Valid taskDTO: TaskWriteDTO): ResponseEntity<Long> {
        val createdId = taskService.create(taskDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateTask(@PathVariable(name = "id") id: Long, @RequestBody @Valid taskDTO: TaskWriteDTO): ResponseEntity<Long> {
        taskService.update(id, taskDTO)
        messagingTemplate.convertAndSend("/topic/tasks", taskService.get(id))
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteTask(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        taskService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/send/{id}")
    @ApiResponse(responseCode = "201")
    fun sendEmailList(@PathVariable(name = "id") id: Long/*taskList: List<Long>*/): ResponseEntity<String> {
        //Reach task with id, to iterate and extract the info required to the email
        //val tasks = taskService.findAllByIds(taskList)
        val task = taskService.get(id)

        val emailTo = listOf(task.supplier?.email)
        val emailDescription = task.name + " starting at " + task.startDate
        val emailToTest = listOf("diegofelipere@gmail.com")
        val params = mapOf(
            "TASK" to "${task.id}",
            "TASKDATA" to "${task.name}"
        )
        val emailSentTest = emailToTest?.let { emailService.sendEmail(it, "test",emailDescription,params) }
        //val emailSentTest = emailService.sendEmail(emailTo, emailDescription)
        return ResponseEntity(emailSentTest, HttpStatus.CREATED)
    }

    @PostMapping("/send")
    @ApiResponse(responseCode = "201")
    fun taskNotification(@RequestBody tasks: List<Long>): ResponseEntity<String> {
        try {
            taskService.taskNotifyEmail(tasks)

            return ResponseEntity.ok("Tasks notification successfully")
        } catch (e: Exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing tasks: ${e.message}")
        }
    }

}
