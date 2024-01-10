package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.FileDTO
import co.innovaciones.bflow_server.service.FileService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import java.lang.Void
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(
    value = ["/api/files"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class FileResource(
    private val fileService: FileService
) {

    @GetMapping
    fun getAllFiles(): ResponseEntity<List<FileDTO>> = ResponseEntity.ok(fileService.findAll())

    @GetMapping("/{id}")
    fun getFile(@PathVariable(name = "id") id: Long): ResponseEntity<FileDTO> =
            ResponseEntity.ok(fileService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createFile(@RequestBody @Valid fileDTO: FileDTO): ResponseEntity<Long> {
        val createdId = fileService.create(fileDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateFile(@PathVariable(name = "id") id: Long, @RequestBody @Valid fileDTO: FileDTO):
            ResponseEntity<Long> {
        fileService.update(id, fileDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteFile(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        fileService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
