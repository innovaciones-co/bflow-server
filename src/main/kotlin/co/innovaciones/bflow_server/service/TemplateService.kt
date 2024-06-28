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
    private val productRepository: ProductRepository,
    private val jobService: JobService,
    private val itemService: ItemService,
) {

    fun findAll(): List<TemplateDTO> {
        val templates = templateRepository.findAll(Sort.by("name"))
        return templates.stream()
                .map { template -> mapToDTO(template, TemplateDTO()) }
                .toList()
    }

    fun findAllByType(type: TemplateType): List<TemplateDTO> {
        val templates = templateRepository.findByType(type, Sort.by("name"))
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
        templateDTO.type = template.type
        templateDTO.name = template.name
        templateDTO.template = template.template
        return templateDTO
    }

    private fun mapToEntity(templateDTO: TemplateDTO, template: Template): Template {
        template.name = templateDTO.name
        template.type = templateDTO.type
        template.template = templateDTO.template
        return template
    }

    fun nameExists(name: String?): Boolean = templateRepository.existsByNameIgnoreCase(name)

    fun createTasks(id: Long, jobId: Long) {
        val job = jobService.get(jobId)
        val template = templateRepository.findById(id)
            .orElseThrow { throw NotFoundException("Template with id $id not found") }

        val tasks = parseTemplateAndCreateTasks(template, job)
        taskRepository.saveAll(tasks)
    }

    fun createMaterials(id: Long, jobId: Long) {
        val job = jobService.get(jobId)
        val template = templateRepository.findById(id)
            .orElseThrow { throw NotFoundException("Template with id $id not found") }

        val items = parseTemplateAndCreateMaterials(template, job)
        itemService.createAll(items)
    }

    private fun parseTemplateAndCreateTasks(template: Template, job: JobDTO): List<Task> {
        val mapper = jacksonObjectMapper()
        val templateTasks : List<TaskTemplateDTO>? = mapper.readValue(template.template!!)

        val tasksDTO : List<TaskWriteDTO> = templateTasks?.let { taskList ->
            taskList.map { taskDefinition ->
                TaskWriteDTO().apply{
                    name = taskDefinition.name
                    startDate = job.plannedStartDate
                    endDate = job.plannedStartDate?.plusDays(1)
                    progress = 0.0
                    status = TaskStatus.CREATED
                    stage = taskDefinition.stage
                    parentTask = null
                    supplier = taskDefinition.supplierId
                    attachments = emptyList()
                    this.job = job.id
                    description = taskDefinition.description
                    order = taskDefinition.order
                }
            }
        } ?: emptyList()

        return tasksDTO.map { taskDTO -> mapToEntity(taskDTO, Task())}
    }

    private fun parseTemplateAndCreateMaterials(template: Template, jobDTO: JobDTO): List<ItemDTO> {
        val mapper = jacksonObjectMapper()
        val templateMaterials: List<MaterialTemplateDTO>? = mapper.readValue(template.template!!)

        return templateMaterials?.mapNotNull { item ->
            val product = productRepository.findFirstByName(item.productName)
            product?.let {
                ItemDTO().apply {
                    id = product.id
                    name = product.name
                    description = product.description
                    unitPrice = product.unitPrice ?: 0.0
                    vat = product.vat
                    units = item.quantity
                    measure = product.unitOfMeasure
                    category = product.category?.id
                    job = jobDTO.id
                    supplier = product.supplier!!.id
                }
            }
        } ?: emptyList()
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
        val job = if (taskDTO.job == null) null else jobRepository.findById(taskDTO.job!!)
            .orElseThrow { NotFoundException("job not found") }
        task.job = job
        task.order = taskDTO.order
        return task
    }

    private fun mapToEntity(taskWriteDTO: TaskWriteDTO, task: Task): Task {
        mapToEntity(taskWriteDTO as TaskDTO, task)
        val attachments = fileRepository.findAllById(taskWriteDTO.attachments ?: emptyList())
        if (attachments.size != (if (taskWriteDTO.attachments == null) 0 else
                taskWriteDTO.attachments!!.size)) {
            throw NotFoundException("one of attachments not found")
        }
        val supplier = if (taskWriteDTO.supplier == null) null else
            contactRepository.findById(taskWriteDTO.supplier!!)
                .orElseThrow { NotFoundException("supplier not found") }
        task.supplier = supplier
        return task
    }

}
