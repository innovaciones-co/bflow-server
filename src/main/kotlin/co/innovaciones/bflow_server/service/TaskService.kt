package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.Contact
import co.innovaciones.bflow_server.domain.Task
import co.innovaciones.bflow_server.model.*
import co.innovaciones.bflow_server.repos.*
import co.innovaciones.bflow_server.util.NotFoundException
import co.innovaciones.bflow_server.util.ReferencedWarning
import jakarta.transaction.Transactional
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.OffsetDateTime


@Service
@Transactional
class TaskService(
    private val taskRepository: TaskRepository,
    private val jobRepository: JobRepository,
    private val contactRepository: ContactRepository,
    private val fileRepository: FileRepository,
    private val emailService: EmailService
) {

    fun findAll(): List<TaskReadDTO> {
        val tasks = taskRepository.findAll(Sort.by("id"))
        return tasks.stream()
            .map { task -> mapToDTO(task, TaskReadDTO()) }
            .toList()
    }

    fun findAllByJob(jobId: Long): List<TaskReadDTO> {
        val job = jobRepository.findById(jobId)
        if (job.isEmpty) return listOf()

        val tasks = taskRepository.findAllByJob(job.get(), Sort.by(Sort.Direction.DESC, "id"))
        return tasks.stream()
            .map { task -> mapToDTO(task, TaskReadDTO()) }
            .toList()
    }

    fun findAllByIds(ids: List<Long>): List<TaskReadDTO> {
        val tasks = taskRepository.findByIdIn(ids)
        return tasks.stream()
            .map { task -> mapToDTO(task, TaskReadDTO()) }
            .toList()
    }

    fun findAllByIds(ids: List<Long>): List<TaskReadDTO> {
        val tasks = taskRepository.findByIdIn(ids)
        return tasks.stream()
            .map { task -> mapToDTO(task, TaskReadDTO()) }
            .toList()
    }



    fun `get`(id: Long): TaskReadDTO = taskRepository.findById(id)
        .map { task -> mapToDTO(task, TaskReadDTO()) }
        .orElseThrow { NotFoundException() }

    fun create(taskDTO: TaskWriteDTO): Long {
        val task = Task()
        mapToEntity(taskDTO, task)
        return taskRepository.save(task).id!!
    }

    fun update(id: Long, taskDTO: TaskWriteDTO) {
        val task = taskRepository.findById(id)
            .orElseThrow { NotFoundException() }
        mapToEntity(taskDTO, task)
        taskRepository.save(task)
    }

    fun delete(id: Long) {
        taskRepository.deleteById(id)
    }

    fun taskNotify(ids: List<Long>) {
        for (id in ids) {
            val task = get(id)
            //emailService.sendEmail(task.supplier?.email, task.name?)
            if (task.supplier == null) {
                continue
            }
            val params = mapOf(
                "TASK" to "${task.id}",
                "TASKDATA" to "${task.name}"
            )
            emailService.sendEmail(
                listOf(task.supplier?.email!!),
                task.name ?: "No name", "TEST", params
            )
            task.status = TaskStatus.SENT
            OffsetDateTime.now().also { task.bookingDate = it }
            update(id, mapToCreateUpdateDTO(task))
        }
    }

    fun taskNotifyEmail(ids: List<Long>) {
        for (id in ids) {
            val task = get(id)
            //emailService.sendEmail(task.supplier?.email, task.name?)
            if (task.supplier == null) {
                continue
            }
            //content according to template
            val params = mapOf(
                "TASK" to "${task.id}",
                "TASKDATA" to "${task.name}"
            )

            val notificationFactory = NotificationFactory()

            val builder =
                notificationFactory.createNotificationBuilder("email", emailService) as EmailNotificationBuilder
            val notification = builder
                .withParams(params)
                .withContent("\"<html><body><h1>Common: This is my first transactional email {{params.TASKDATA}}</h1></body></html>")
                .withSubject("Notification for task: ${task.id}")
                .withRecipients("diegofelipere@gmail.com")
                .build()
            notification.send()

            //task.status = TaskStatus.SENT
            //OffsetDateTime.now().also { task.bookingDate = it }
            //update(id, mapToCreateUpdateDTO(task))

        }
    }

    private fun mapToDTO(task: Task, taskDTO: TaskDTO): TaskDTO {
        taskDTO.id = task.id
        taskDTO.name = task.name
        taskDTO.startDate = task.startDate
        taskDTO.endDate = task.endDate
        taskDTO.description = task.description
        taskDTO.progress = task.progress
        taskDTO.status = task.status
        taskDTO.stage = task.stage
        taskDTO.parentTask = task.parentTask?.id
        taskDTO.attachments = task.attachments?.stream()
            ?.map { file -> file.id!! }
            ?.toList()
        taskDTO.job = task.job?.id
        taskDTO.order = if (task.order != null) task.order!! else 0
        return taskDTO
    }

    private fun mapToDTO(task: Task, taskDTO: TaskReadDTO): TaskReadDTO {
        mapToDTO(task, taskDTO as TaskDTO)
        taskDTO.supplier = task.supplier?.let { supplier -> contactService.mapToDTO(supplier, ContactDTO()) }
        taskDTO.bookingDate = task.callDate

        return taskDTO
    }
////
    /*private fun mapToDTO(task: TaskReadDTO, taskDTO: TaskCreateUpdateDTO): TaskCreateUpdateDTO {
        mapToDTO(task, taskDTO as TaskDTO)
        task.supplier?.let { supplier -> mapToDTO(supplier, ContactDTO()) }.also { taskDTO.supplier = it }
        //taskDTO.supplier = task.supplier?.let { supplier -> mapToDTO(supplier, ContactDTO()) }

        return taskDTO
    }*/

    private fun mapToCreateUpdateDTO(taskReadDTO: TaskReadDTO): TaskCreateUpdateDTO {
        val taskCreateUpdateDTO = TaskCreateUpdateDTO()
        //mapToDTO(taskReadDTO as Task, taskCreateUpdateDTO as TaskDTO)


        taskCreateUpdateDTO.id = taskReadDTO.id
        taskCreateUpdateDTO.name = taskReadDTO.name
        taskCreateUpdateDTO.startDate = taskReadDTO.startDate
        taskCreateUpdateDTO.endDate = taskReadDTO.endDate
        taskCreateUpdateDTO.description = taskReadDTO.description
        taskCreateUpdateDTO.progress = taskReadDTO.progress
        taskCreateUpdateDTO.status = taskReadDTO.status
        taskCreateUpdateDTO.stage = taskReadDTO.stage
        taskCreateUpdateDTO.parentTask = taskReadDTO.parentTask
        taskCreateUpdateDTO.attachments = taskReadDTO.attachments
        taskCreateUpdateDTO.job = taskReadDTO.job

        taskCreateUpdateDTO.supplier = taskReadDTO.supplier?.id

        return taskCreateUpdateDTO
    }


    ////
    private fun mapToEntity(taskDTO: TaskDTO, task: Task): Task {
        task.name = taskDTO.name
        task.startDate = taskDTO.startDate
        task.endDate = taskDTO.endDate
        task.status = taskDTO.status
        task.progress = if(taskDTO.status == TaskStatus.COMPLETED) 100.0 else taskDTO.progress
        task.stage = taskDTO.stage
        val parentTask = if (taskDTO.parentTask == null) null else
            taskRepository.findById(taskDTO.parentTask!!)
                .orElseThrow { NotFoundException("parentTask not found") }

        if (parentTask != null) {
            val startDateIsValid = taskDTO.startDate!!.isAfter(parentTask.startDate)
            val endDateIsValid =
                taskDTO.endDate!!.isBefore(parentTask.endDate) || taskDTO.endDate!!.isEqual(parentTask.endDate)
        }

        task.parentTask = parentTask
        val attachments = fileRepository.findAllById(taskDTO.attachments ?: emptyList())
        if (attachments.size != (if (taskDTO.attachments == null) 0 else
                taskDTO.attachments!!.size)
        ) {
            throw NotFoundException("one of attachments not found")
        }
        task.attachments = attachments.toMutableSet()
        val job = if (taskDTO.job == null) null else jobRepository.findById(taskDTO.job!!)
            .orElseThrow { NotFoundException("job not found") }
        task.job = job
        task.description = taskDTO.description
        task.order = taskDTO.order
        return task
    }

    private fun mapToEntity(taskWriteDTO: TaskWriteDTO, task: Task): Task {
        mapToEntity(taskWriteDTO as TaskDTO, task)
        val supplier = if (taskWriteDTO.supplier == null) null else
            contactRepository.findById(taskWriteDTO.supplier!!)
                .orElseThrow { NotFoundException("supplier not found") }
        task.supplier = supplier
        return task
    }

    fun getReferencedWarning(id: Long): ReferencedWarning? {
        val referencedWarning = ReferencedWarning()
        val task = taskRepository.findById(id)
            .orElseThrow { NotFoundException() }
        val parentTaskTask = taskRepository.findFirstByParentTaskAndIdNot(task, task.id)
        if (parentTaskTask != null) {
            referencedWarning.key = "task.task.parentTask.referenced"
            referencedWarning.addParam(parentTaskTask.id)
            return referencedWarning
        }
        return null
    }
}
