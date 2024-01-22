package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.*
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
    private val userRepository: UserRepository
) {

    @Transactional(readOnly = true)
    fun findAll(): List<JobDTO> {
        val jobs = jobRepository.findAll(Sort.by("id"))
        return jobs.stream()
                .map { job -> mapToDTO(job, JobDTO()) }
                .toList()
    }

    @Transactional(readOnly = true)
    fun `get`(id: Long): JobDTO = jobRepository.findById(id)
            .map { job -> mapToDTO(job, JobDTO(), true) }
            .orElseThrow { NotFoundException() }

    fun create(jobDTO: JobDTO): Long {
        val job = Job()
        mapToEntity(jobDTO, job)
        return jobRepository.save(job).id!!
    }

    fun update(id: Long, jobDTO: JobDTO) {
        val job = jobRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(jobDTO, job)
        jobRepository.save(job)
    }

    fun delete(id: Long) {
        jobRepository.deleteById(id)
    }

    private fun mapToDTO(job: Job, jobDTO: JobDTO, includeChildren: Boolean = false): JobDTO {
        jobDTO.id = job.id
        jobDTO.jobNumber = job.jobNumber
        jobDTO.name = job.name
        jobDTO.plannedStartDate = job.plannedStartDate
        jobDTO.plannedEndDate = job.plannedEndDate
        jobDTO.address = job.address
        jobDTO.description = job.description
        jobDTO.buildingType = job.buildingType
        jobDTO.client = job.client?.id
        jobDTO.user = job.user?.let{ user -> mapToDTO(user, UserDTO()) }
        jobDTO.stage = calculateStage(job.tasks)
        jobDTO.progress = calculateJobCompletionPercentage(job.tasks)
        if (includeChildren) {
            jobDTO.notes = job.notes?.map { note -> mapNoteToDTO(note, NoteDTO()) }?.toSet()
            jobDTO.files = job.files?.map { file -> mapFileToDTO(file, FileDTO()) }?.toSet()
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

    private fun mapFileToDTO(file: File, fileDTO: FileDTO): FileDTO {
        fileDTO.id = file.id
        fileDTO.uuid = file.uuid
        fileDTO.name = file.name
        fileDTO.type = file.type
        fileDTO.category = file.category
        fileDTO.tag = file.tag
        fileDTO.job = file.job?.id
        return fileDTO
    }

    private fun mapToEntity(jobDTO: JobDTO, job: Job): Job {
        job.jobNumber = jobDTO.jobNumber
        job.name = jobDTO.name
        job.plannedStartDate = jobDTO.plannedStartDate
        job.plannedEndDate = jobDTO.plannedEndDate
        job.address = jobDTO.address
        job.description = jobDTO.description
        job.buildingType = jobDTO.buildingType
        val client = if (jobDTO.client == null) null else
                contactRepository.findById(jobDTO.client!!)
                .orElseThrow { NotFoundException("client not found") }
        job.client = client
        val user = if (jobDTO.user?.id == null) null else userRepository.findById(jobDTO.user!!.id!!)
                .orElseThrow { NotFoundException("user not found") }
        job.user = user
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

    private fun calculateJobCompletionPercentage(tasks : MutableSet<Task>?): Double {
        val totalTasks = tasks?.size ?: 0
        if (totalTasks == 0) {
            return 0.0
        }

        val completedTasks = tasks!!.count { it.status == TaskStatus.COMPLETED }
        return (completedTasks.toDouble() / totalTasks) * 100
    }

    private fun calculateStage(tasks : MutableSet<Task>?): TaskStage {
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
