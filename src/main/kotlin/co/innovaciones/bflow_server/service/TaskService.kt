package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.Task
import co.innovaciones.bflow_server.model.*
import co.innovaciones.bflow_server.repos.ContactRepository
import co.innovaciones.bflow_server.repos.FileRepository
import co.innovaciones.bflow_server.repos.JobRepository
import co.innovaciones.bflow_server.repos.TaskRepository
import co.innovaciones.bflow_server.util.NotFoundException
import co.innovaciones.bflow_server.util.ReferencedWarning
import jakarta.transaction.Transactional
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.OffsetDateTime


@Service
@Transactional
class TaskService(
    private val taskRepository: TaskRepository,
    private val jobRepository: JobRepository,
    private val contactRepository: ContactRepository,
    private val fileRepository: FileRepository,
    private val emailService: EmailService,
    private val fileService: FileService,
    private val contactService: ContactService
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


    fun `get`(id: Long): TaskReadDTO = taskRepository.findById(id)
        .map { task -> mapToDTO(task, TaskReadDTO()) }
        .orElseThrow { NotFoundException() }

    fun create(taskDTO: TaskWriteDTO): Long {
        if (taskDTO.order == null )
            taskDTO.order = (findAllByJob(taskDTO.id!!).maxByOrNull { it.order ?: 0}?.order ?: -1 ) + 1
        val task = Task()
        mapToEntity(taskDTO, task)
        return taskRepository.save(task).id!!
    }

    fun update(id: Long, taskDTO: TaskWriteDTO) {
        val task = taskRepository.findById(id)
            .orElseThrow { NotFoundException() }
        if (taskDTO.order == null || task.order == null)
            taskDTO.order = (findAllByJob(taskDTO.id!!).maxByOrNull { it.order ?: 0}?.order ?: -1 ) + 1
        mapToEntity(taskDTO, task)
        taskRepository.save(task)
    }

    fun update(tasksDTO: List<TaskWriteDTO>) {
        val tasks = tasksDTO.map { taskDTO ->
            val task = taskRepository.findById(taskDTO.id!!)
                .orElseThrow { NotFoundException() }
            mapToEntity(taskDTO, task)
            task
        }

        taskRepository.saveAll(tasks)
    }

    fun delete(id: Long) {
        taskRepository.deleteById(id)
    }

    fun taskNotifyEmail(ids: List<Long>) {
        for (id in ids) {
            val task = taskRepository.findById(id).get()
            if (task.supplier == null || task.supplier?.email == null) {
                continue
            }

            val job = task.job
            val params = mapOf(
                "taskName" to "${task.name}",
                "jobNumber" to "${job?.jobNumber}",
                "address" to "${job?.address}",
                "supervisor" to "${job?.supervisor?.firstName} ${job?.supervisor?.lastName}",
                "supervisorEmail" to "${job?.supervisor?.email}",
                "supplier" to "${task.supplier?.name}",
                "supplierEmail" to "${task.supplier?.email}",
                "startDate" to "${task.startDate}",
                "endDate" to "${task.endDate}",
                "comments" to if (task.description != null) "${task.description}" else "",
                "taskId" to "${task.id}",
                "rejectURL" to "http://localhost:8080/tasks/${task.id}?action=reject",
                "rescheduleURL" to "http://localhost:8080/tasks/${task.id}?action=reschedule",
                "confirmURL" to "http://localhost:8080/tasks/${task.id}?action=confirm"
            )

            val notificationFactory = NotificationFactory()

            val builder =
                notificationFactory.createNotificationBuilder("email", emailService) as EmailNotificationBuilder
            val notification = builder
                .withTemplate(1)
                .withParams(params)
                .withSubject("A new task was assigned to you (${task.id})")
                .withRecipients(task.supplier!!.email!!, "testsh@mailinator.com", "test@mailinator.com")
                .build()
            notification.send()

            task.status = TaskStatus.SENT
            task.callDate = OffsetDateTime.now()
            taskRepository.save(task)
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
        taskDTO.job = task.job?.id
        taskDTO.order = if (task.order != null) task.order!! else 0
        taskDTO.bookingDate = task.callDate

        return taskDTO
    }

    private fun mapToDTO(task: Task, taskDTO: TaskReadDTO): TaskReadDTO {
        mapToDTO(task, taskDTO as TaskDTO)
        taskDTO.supplier = task.supplier?.let { supplier -> contactService.mapToDTO(supplier, ContactDTO()) }
        taskDTO.attachments = task.attachments?.stream()
            ?.map { file -> fileService.mapToDTO(file, FileDTO())}
            ?.toList()

        return taskDTO
    }

    private fun mapToEntity(taskDTO: TaskDTO, task: Task): Task {
        task.name = taskDTO.name
        task.startDate = taskDTO.startDate
        task.endDate = taskDTO.endDate
        task.status = taskDTO.status
        task.progress = if (taskDTO.status == TaskStatus.COMPLETED) 100.0 else taskDTO.progress
        task.stage = taskDTO.stage
        val parentTask = if (taskDTO.parentTask == null) null else
            taskRepository.findById(taskDTO.parentTask!!)
                .orElseThrow { NotFoundException("parentTask not found") }
        task.parentTask = parentTask
        val job = if (taskDTO.job == null) null else jobRepository.findById(taskDTO.job!!)
            .orElseThrow { NotFoundException("job not found") }
        task.job = job
        task.description = taskDTO.description
        task.order = taskDTO.order
        return task
    }

    private fun mapToEntity(taskWriteDTO: TaskWriteDTO, task: Task): Task {
        mapToEntity(taskWriteDTO as TaskDTO, task)
        val attachments = fileRepository.findAllById(taskWriteDTO.attachments ?: emptyList()).toMutableSet()
        if (attachments.size != (if (taskWriteDTO.attachments == null) 0 else
                taskWriteDTO.attachments!!.size)) {
            throw NotFoundException("one of attachments not found")
        }
        task.attachments = attachments
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
