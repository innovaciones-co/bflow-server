package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.File
import co.innovaciones.bflow_server.model.FileDTO
import co.innovaciones.bflow_server.repos.FileRepository
import co.innovaciones.bflow_server.repos.JobRepository
import co.innovaciones.bflow_server.repos.TaskRepository
import co.innovaciones.bflow_server.util.NotFoundException
import jakarta.transaction.Transactional
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
@Transactional
class FileService(
    private val fileRepository: FileRepository,
    private val jobRepository: JobRepository,
    private val taskRepository: TaskRepository
) {

    fun findAll(): List<FileDTO> {
        val files = fileRepository.findAll(Sort.by("id"))
        return files.stream()
                .map { file -> mapToDTO(file, FileDTO()) }
                .toList()
    }

    fun `get`(id: Long): FileDTO = fileRepository.findById(id)
            .map { file -> mapToDTO(file, FileDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(fileDTO: FileDTO): Long {
        val file = File()
        mapToEntity(fileDTO, file)
        return fileRepository.save(file).id!!
    }

    fun update(id: Long, fileDTO: FileDTO) {
        val file = fileRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(fileDTO, file)
        fileRepository.save(file)
    }

    fun delete(id: Long) {
        val file = fileRepository.findById(id)
                .orElseThrow { NotFoundException() }
        // remove many-to-many relations at owning side
        taskRepository.findAllByAttachments(file)
                .forEach { task -> task.attachments!!.remove(file) }
        fileRepository.delete(file)
    }

    private fun mapToDTO(`file`: File, fileDTO: FileDTO): FileDTO {
        fileDTO.id = file.id
        fileDTO.uri = file.uri
        fileDTO.name = file.name
        fileDTO.type = file.type
        fileDTO.category = file.category
        fileDTO.tag = file.tag
        fileDTO.job = file.job?.id
        return fileDTO
    }

    private fun mapToEntity(fileDTO: FileDTO, `file`: File): File {
        file.uri = fileDTO.uri
        file.name = fileDTO.name
        file.type = fileDTO.type
        file.category = fileDTO.category
        file.tag = fileDTO.tag
        val job = if (fileDTO.job == null) null else jobRepository.findById(fileDTO.job!!)
                .orElseThrow { NotFoundException("job not found") }
        file.job = job
        return file
    }

    fun uriExists(uri: String?): Boolean = fileRepository.existsByUriIgnoreCase(uri)

}
