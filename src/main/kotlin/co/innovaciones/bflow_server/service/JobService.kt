package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.Job
import co.innovaciones.bflow_server.model.JobDTO
import co.innovaciones.bflow_server.repos.ContactRepository
import co.innovaciones.bflow_server.repos.JobRepository
import co.innovaciones.bflow_server.repos.UserRepository
import co.innovaciones.bflow_server.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class JobService(
    private val jobRepository: JobRepository,
    private val contactRepository: ContactRepository,
    private val userRepository: UserRepository
) {

    fun findAll(): List<JobDTO> {
        val jobs = jobRepository.findAll(Sort.by("id"))
        return jobs.stream()
                .map { job -> mapToDTO(job, JobDTO()) }
                .toList()
    }

    fun `get`(id: Long): JobDTO = jobRepository.findById(id)
            .map { job -> mapToDTO(job, JobDTO()) }
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

    private fun mapToDTO(job: Job, jobDTO: JobDTO): JobDTO {
        jobDTO.id = job.id
        jobDTO.jobNumber = job.jobNumber
        jobDTO.name = job.name
        jobDTO.plannedStartDate = job.plannedStartDate
        jobDTO.plannedEndDate = job.plannedEndDate
        jobDTO.address = job.address
        jobDTO.contract = job.contract
        jobDTO.description = job.description
        jobDTO.buildingType = job.buildingType
        jobDTO.client = job.client?.id
        jobDTO.user = job.user?.id
        return jobDTO
    }

    private fun mapToEntity(jobDTO: JobDTO, job: Job): Job {
        job.jobNumber = jobDTO.jobNumber
        job.name = jobDTO.name
        job.plannedStartDate = jobDTO.plannedStartDate
        job.plannedEndDate = jobDTO.plannedEndDate
        job.address = jobDTO.address
        job.contract = jobDTO.contract
        job.description = jobDTO.description
        job.buildingType = jobDTO.buildingType
        val client = if (jobDTO.client == null) null else
                contactRepository.findById(jobDTO.client!!)
                .orElseThrow { NotFoundException("client not found") }
        job.client = client
        val user = if (jobDTO.user == null) null else userRepository.findById(jobDTO.user!!)
                .orElseThrow { NotFoundException("user not found") }
        job.user = user
        return job
    }

    fun jobNumberExists(jobNumber: String?): Boolean =
            jobRepository.existsByJobNumberIgnoreCase(jobNumber)

}
