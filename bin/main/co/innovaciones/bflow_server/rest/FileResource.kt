package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.CreateFileDTO
import co.innovaciones.bflow_server.model.FileDTO
import co.innovaciones.bflow_server.service.FileService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["/api/files"], produces = [MediaType.APPLICATION_JSON_VALUE]
)
@SecurityRequirement(name = "bearer-jwt")
class FileResource(
    private val fileService: FileService
) {
    private val logger: Logger = LoggerFactory.getLogger(FileResource::class.java)

    @GetMapping
    fun getAllFiles(): ResponseEntity<List<FileDTO>> {
        logger.info("GET /api/files called")
        return ResponseEntity.ok(fileService.findAll())
    }

    @GetMapping("/{id}")
    fun getFile(@PathVariable(name = "id") id: Long): ResponseEntity<FileDTO> {
        logger.info("GET /api/files/{} called", id)
        return ResponseEntity.ok(fileService.get(id))
    }

    @PostMapping("/upload")
    @ApiResponse(responseCode = "201")
    fun uploadFile(@ModelAttribute @Valid createFileDTO: CreateFileDTO): ResponseEntity<Long> {
        logger.info("POST /api/files/upload called with createFileDTO: {}", createFileDTO)
        val createdId = fileService.uploadObject(createFileDTO)
        logger.info("File uploaded with id: {}", createdId)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createFile(@RequestBody @Valid fileDTO: FileDTO): ResponseEntity<Long> {
        logger.info("POST /api/files called with fileDTO: {}", fileDTO)
        val createdId = fileService.create(fileDTO)
        logger.info("File created with id: {}", createdId)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateFile(@PathVariable(name = "id") id: Long, @RequestBody @Valid fileDTO: FileDTO): ResponseEntity<Long> {
        logger.info("PUT /api/files/{} called with fileDTO: {}", id, fileDTO)
        fileService.update(id, fileDTO)
        logger.info("File with id: {} updated", id)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteFile(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        logger.info("DELETE /api/files/{} called", id)
        fileService.delete(id)
        logger.info("File with id: {} deleted", id)
        return ResponseEntity.noContent().build()
    }
}
