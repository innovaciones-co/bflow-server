package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.File
import co.innovaciones.bflow_server.model.CreateFileDTO
import co.innovaciones.bflow_server.model.FileDTO
import co.innovaciones.bflow_server.model.FileEntity
import co.innovaciones.bflow_server.repos.FileRepository
import co.innovaciones.bflow_server.repos.JobRepository
import co.innovaciones.bflow_server.repos.TaskRepository
import co.innovaciones.bflow_server.util.NotFoundException
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.model.NoSuchBucketException
import java.util.*


@Service
@Transactional
class FileService(
    private val fileRepository: FileRepository,
    private val jobRepository: JobRepository,
    private val taskRepository: TaskRepository,
    private val s3Service: S3ServiceI
) {

    @Value("\${aws.s3.default-bucket}")
    private val defaultBucket: String = ""
    private val log = LoggerFactory.getLogger(this.javaClass)

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
        try {
            s3Service.deleteObject(file.bucket, file.uuid)
        }
        catch ( e: NoSuchBucketException) {
            println("Bucked do not exists")
        }
        fileRepository.delete(file)
    }

    fun uploadObject(createFileDTO: CreateFileDTO) : Long {
        val file = createFileDTO.file!!
        val reqBody = RequestBody.fromBytes(file.bytes)
        val key = UUID.randomUUID()

        log.debug("Uploading {}...", key)

        val bucket = getBucketFromType(createFileDTO.entity)
        s3Service.uploadObject(bucket, key.toString(), reqBody)
        val fileDTO = mapToDTO(key, createFileDTO, file)

        return this.create(fileDTO)
    }

    private fun mapToDTO(
        key: UUID,
        createFileDTO: CreateFileDTO,
        file: MultipartFile
    ) : FileDTO {
        val fileDTO = FileDTO()
        fileDTO.uuid = key.toString()
        fileDTO.name = createFileDTO.name
        fileDTO.category = createFileDTO.category
        fileDTO.type = file.contentType
        fileDTO.job = createFileDTO.job
        fileDTO.temporaryUrl = s3Service.getTemporaryUrl(getBucketFromType(createFileDTO.entity), fileDTO.uuid)
        fileDTO.bucket = getBucketFromType(createFileDTO.entity)
        fileDTO.tag = createFileDTO.tag
        return fileDTO
    }

    fun mapToDTO(`file`: File, fileDTO: FileDTO): FileDTO {
        fileDTO.id = file.id
        fileDTO.uuid = file.uuid
        fileDTO.name = file.name
        fileDTO.type = file.type
        fileDTO.category = file.category
        fileDTO.tag = file.tag
        fileDTO.job = file.job?.id
        fileDTO.temporaryUrl = s3Service.getTemporaryUrl(file.bucket, fileDTO.uuid)
        fileDTO.bucket = file.bucket
        return fileDTO
    }

    private fun mapToEntity(fileDTO: FileDTO, `file`: File): File {
        file.uuid = if(fileDTO.uuid != null) fileDTO.uuid!! else  ""
        file.name = fileDTO.name
        file.type = fileDTO.type
        file.category = fileDTO.category
        file.tag = fileDTO.tag
        val job = if (fileDTO.job == null) null else jobRepository.findById(fileDTO.job!!)
                .orElseThrow { NotFoundException("job not found") }
        file.job = job
        file.bucket = fileDTO.bucket!!
        return file
    }

    fun uuidExists(uri: String?): Boolean = fileRepository.existsByUuidIgnoreCase(uri)

    private fun getBucketFromType(entity: FileEntity) = when(entity) {
        FileEntity.JOB -> this.defaultBucket + "-job"
        FileEntity.TASK -> this.defaultBucket + "-task"
    }

}
