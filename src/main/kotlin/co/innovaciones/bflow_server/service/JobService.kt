package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.*
import co.innovaciones.bflow_server.mappers.PurchaseOrderMapper
import co.innovaciones.bflow_server.model.*
import co.innovaciones.bflow_server.repos.ContactRepository
import co.innovaciones.bflow_server.repos.JobRepository
import co.innovaciones.bflow_server.repos.UserRepository
import co.innovaciones.bflow_server.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class JobService(
    private val jobRepository: JobRepository,
    private val contactRepository: ContactRepository,
    private val userRepository: UserRepository,
    private val fileService: FileService,
    private val contactService: ContactService,
    private val purchaseOrderMapper: PurchaseOrderMapper
) {

    @Transactional(readOnly = true)
    fun findAll(): List<JobReadDTO> {
        val jobs = jobRepository.findAll(Sort.by("id"))
        return jobs.stream()
            .map { job -> mapToDTO(job, JobReadDTO()) }
            .toList()
    }

    @Transactional(readOnly = true)
    fun `get`(id: Long): JobReadDTO = jobRepository.findById(id)
        .map { job -> mapToDTO(job, JobReadDTO(), true) }
        .orElseThrow { NotFoundException() }

    fun create(jobDTO: JobWriteDTO): Long {

        val job = Job()
        mapToEntity(jobDTO, job)
        return jobRepository.save(job).id!!
    }

    fun update(id: Long, jobDTO: JobWriteDTO) {
        val job = jobRepository.findById(id)
            .orElseThrow { NotFoundException() }
        mapToEntity(jobDTO, job)
        jobRepository.save(job)
    }

    fun delete(id: Long) {
        jobRepository.deleteById(id)
    }

    protected fun mapToDTO(job: Job, jobDTO: JobReadDTO, includeChildren: Boolean = false): JobReadDTO {
        jobDTO.id = job.id
        jobDTO.jobNumber = job.jobNumber
        jobDTO.name = job.name
        jobDTO.plannedStartDate = job.plannedStartDate
        jobDTO.plannedEndDate = job.plannedEndDate
        jobDTO.address = job.address
        jobDTO.description = job.description
        jobDTO.buildingType = job.buildingType
        jobDTO.client = job.client?.let { client -> contactService.mapToDTO(client, ContactDTO()) }
        jobDTO.user = job.user?.let { user -> mapToDTO(user, UserDTO()) }
        jobDTO.stage = calculateStage(job.tasks)
        jobDTO.progress = calculateJobCompletionPercentage(job.tasks)
        jobDTO.supervisor = job.supervisor?.let { user -> mapToDTO(user, UserDTO()) }
        if (includeChildren) {
            jobDTO.notes = job.notes?.map { note -> mapNoteToDTO(note, NoteDTO()) }?.toSet()
            jobDTO.files = job.files?.map { file -> fileService.mapToDTO(file, FileDTO()) }?.toSet()
            jobDTO.purchaseOrders = job.purchaseOrders?.map { purchaseOrder ->
                purchaseOrderMapper.mapToDTO(
                    purchaseOrder,
                    PurchaseOrderDTO()
                )
            }?.toSet()
        }
        return jobDTO
    }

    private fun mapToDTO(user: User, userDTO: UserDTO): UserDTO {
        userDTO.id = user.id
        userDTO.firstName = user.firstName
        userDTO.lastName = user.lastName
        userDTO.username = user.username
        userDTO.password = user.password
        userDTO.email = user.email
        return userDTO
    }

    private fun mapToEntity(jobDTO: JobWriteDTO, job: Job): Job {
        job.jobNumber = jobDTO.jobNumber
        job.name = jobDTO.name
        job.plannedStartDate = jobDTO.plannedStartDate
        job.plannedEndDate = jobDTO.plannedEndDate
        job.address = jobDTO.address
        job.description = jobDTO.description
        job.buildingType = jobDTO.buildingType
        job.client = if (jobDTO.client == null) null else
            contactRepository.findById(jobDTO.client!!)
                .orElseThrow { NotFoundException("client not found") }
        job.supervisor = if (jobDTO.supervisor == null) null else userRepository.findById(jobDTO.supervisor!!)
            .orElseThrow { NotFoundException("user not found") }
        job.user = if (jobDTO.user == null) null else userRepository.findById(jobDTO.user!!)
            .orElseThrow { NotFoundException("user not found") }
        return job
    }

    private fun mapNoteToDTO(note: Note, noteDTO: NoteDTO): NoteDTO {
        noteDTO.id = note.id
        noteDTO.body = note.body
        noteDTO.job = note.job?.id
        return noteDTO
    }

    fun jobNumberExists(jobNumber: String?): Boolean =
        jobRepository.existsByJobNumberIgnoreCase(jobNumber)

    private fun calculateJobCompletionPercentage(tasks: MutableSet<Task>?): Double {
        val totalTasks = tasks?.size ?: 0
        if (totalTasks == 0) {
            return 0.0
        }

        val completedTasks = tasks!!.count { it.status == TaskStatus.COMPLETED }
        return (completedTasks.toDouble() / totalTasks) * 100
    }

    private fun calculateStage(tasks: MutableSet<Task>?): TaskStage {
        val totalTasks = tasks?.size ?: 0
        if (totalTasks == 0) {
            return TaskStage.SLAB_DOWN
        }

        val filteredTasks = tasks
            ?.filter { it.status == TaskStatus.COMPLETED }
            ?.sortedByDescending { it.lastUpdated }

        return filteredTasks?.firstOrNull()?.stage ?: TaskStage.SLAB_DOWN
    }

}
