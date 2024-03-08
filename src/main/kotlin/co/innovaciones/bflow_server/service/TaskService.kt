package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.Contact
import co.innovaciones.bflow_server.domain.Task
import co.innovaciones.bflow_server.model.*
import co.innovaciones.bflow_server.repos.*
import co.innovaciones.bflow_server.util.NotFoundException
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

    fun `get`(id: Long): TaskReadDTO = taskRepository.findById(id)
        .map { task -> mapToDTO(task, TaskReadDTO()) }
        .orElseThrow { NotFoundException() }

    fun create(taskDTO: TaskCreateUpdateDTO): Long {
        val task = Task()
        mapToEntity(taskDTO, task)
        return taskRepository.save(task).id!!
    }

    fun update(id: Long, taskDTO: TaskCreateUpdateDTO) {
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
            emailService.sendEmail(
                task.supplier?.email ?: "test@gmail.com",
                task.name ?: "No Name"
            )
            task.status = TaskStatus.SENT
            OffsetDateTime.now().also { task.bookingDate = it }
            update(id, mapToCreateUpdateDTO(task))
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
        return taskDTO
    }

    private fun mapToDTO(task: Task, taskDTO: TaskReadDTO): TaskReadDTO {
        mapToDTO(task, taskDTO as TaskDTO)
        taskDTO.supplier = task.supplier?.let { supplier -> mapToDTO(supplier, ContactDTO()) }
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
        task.progress = taskDTO.progress
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
        return task
    }

    private fun mapToEntity(taskCreateUpdateDTO: TaskCreateUpdateDTO, task: Task): Task {
        mapToEntity(taskCreateUpdateDTO as TaskDTO, task)
        val supplier = if (taskCreateUpdateDTO.supplier == null) null else
            contactRepository.findById(taskCreateUpdateDTO.supplier!!)
                .orElseThrow { NotFoundException("supplier not found") }
        task.supplier = supplier
        return task
    }

    private fun mapToDTO(contact: Contact, contactDTO: ContactDTO): ContactDTO {
        contactDTO.id = contact.id
        contactDTO.name = contact.name
        contactDTO.address = contact.address
        contactDTO.email = contact.email
        contactDTO.type = contact.type
        return contactDTO
    }

}
