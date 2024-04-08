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


@Service
@Transactional
class TaskService(
    private val taskRepository: TaskRepository,
    private val jobRepository: JobRepository,
    private val contactRepository: ContactRepository,
    private val fileRepository: FileRepository
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

        val tasks = taskRepository.findAllByJob(job.get(), Sort.by( Sort.Direction.DESC, "id" ))
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
        taskDTO.supplier = task.supplier?.let { supplier -> mapToDTO(supplier, ContactDTO()) }
        taskDTO.bookingDate = task.callDate

        return taskDTO
    }

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

        task.parentTask = parentTask
        val attachments = fileRepository.findAllById(taskDTO.attachments ?: emptyList())
        if (attachments.size != (if (taskDTO.attachments == null) 0 else
                taskDTO.attachments!!.size)) {
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

    private fun mapToDTO(contact: Contact, contactDTO: ContactDTO): ContactDTO {
        contactDTO.id = contact.id
        contactDTO.name = contact.name
        contactDTO.address = contact.address
        contactDTO.email = contact.email
        contactDTO.type = contact.type
        return contactDTO
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
