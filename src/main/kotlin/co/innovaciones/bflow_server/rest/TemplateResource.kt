package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.JobDTO
import co.innovaciones.bflow_server.model.TemplateDTO
import co.innovaciones.bflow_server.service.TemplateService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
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
    value = ["/api/templates"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@SecurityRequirement(name = "bearer-jwt")
class TemplateResource(
    private val templateService: TemplateService
) {

    @GetMapping
    fun getAllTemplates(): ResponseEntity<List<TemplateDTO>> =
            ResponseEntity.ok(templateService.findAll())

    @GetMapping("/{id}")
    fun getTemplate(@PathVariable(name = "id") id: Long): ResponseEntity<TemplateDTO> =
            ResponseEntity.ok(templateService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createTemplate(@RequestBody @Valid templateDTO: TemplateDTO): ResponseEntity<Long> {
        val createdId = templateService.create(templateDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateTemplate(@PathVariable(name = "id") id: Long, @RequestBody @Valid
            templateDTO: TemplateDTO): ResponseEntity<Long> {
        templateService.update(id, templateDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteTemplate(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        templateService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/{id}")
    @ApiResponse(responseCode = "202")
    fun createTasksFromTemplate(@PathVariable(name = "id") id: Long, @RequestBody @Valid jobDTO: JobDTO): ResponseEntity<Void> {
        templateService.createTasks(id, jobDTO)
        return ResponseEntity.noContent().build()
    }

}
