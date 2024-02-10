package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.Task
import co.innovaciones.bflow_server.domain.Template
import co.innovaciones.bflow_server.model.*
import co.innovaciones.bflow_server.repos.*
import co.innovaciones.bflow_server.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

@Service
class TemplateService(
    private val templateRepository: TemplateRepository,
    private val taskRepository: TaskRepository,
    private val contactRepository: ContactRepository,
    private val jobRepository: JobRepository,
    private val fileRepository: FileRepository,
    private val jobService: JobService
) {

    fun findAll(): List<TemplateDTO> {
        val templates = templateRepository.findAll(Sort.by("id"))
        return templates.stream()
                .map { template -> mapToDTO(template, TemplateDTO()) }
                .toList()
    }

    fun `get`(id: Long): TemplateDTO = templateRepository.findById(id)
            .map { template -> mapToDTO(template, TemplateDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(templateDTO: TemplateDTO): Long {
        val template = Template()
        mapToEntity(templateDTO, template)
        return templateRepository.save(template).id!!
    }

    fun update(id: Long, templateDTO: TemplateDTO) {
        val template = templateRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(templateDTO, template)
        templateRepository.save(template)
    }

    fun delete(id: Long) {
        templateRepository.deleteById(id)
    }

    private fun mapToDTO(template: Template, templateDTO: TemplateDTO): TemplateDTO {
        templateDTO.id = template.id
        templateDTO.name = template.name
        templateDTO.template = template.template
        return templateDTO
    }

    private fun mapToEntity(templateDTO: TemplateDTO, template: Template): Template {
        template.name = templateDTO.name
        template.template = templateDTO.template
        return template
    }

    //fun nameExists(name: String?): Boolean = templateRepository.existsByNameIgnoreCase(name)

    fun createTasks(id: Long, jobId: Long) {
        val job = jobService.get(jobId)
        val template = templateRepository.findById(id)
            .orElseThrow { throw NotFoundException("Template with id $id not found") }

        val tasks = parseTemplateAndCreateTasks(template, job)
        taskRepository.saveAll(tasks)
    }

    private fun parseTemplateAndCreateTasks(template: Template, job: JobDTO): List<Task> {
        val mapper = jacksonObjectMapper()
        val templateTasks : List<TaskTemplateDTO>? = mapper.readValue(template.template!!)

        val tasksDTO : List<TaskCreateUpdateDTO> = templateTasks?.let { taskList ->
            taskList.map { taskDefinition ->
                TaskCreateUpdateDTO().apply{
                    name = taskDefinition.name
                    startDate = job.plannedStartDate
                    endDate = job.plannedEndDate
                    progress = 0.0
                    status = TaskStatus.CREATED
                    stage = taskDefinition.stage
                    parentTask = null
                    supplier = taskDefinition.supplierId
                    attachments = emptyList()
                    this.job = job.id
                    description = taskDefinition.description
                    order = 0

                }
            }
        } ?: emptyList()

        return tasksDTO.map { taskDTO -> mapToEntity(taskDTO, Task())}
    }

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
        task.order = taskDTO.order
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

}
