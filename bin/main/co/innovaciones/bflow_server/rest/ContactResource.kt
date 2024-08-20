package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.ContactDTO
import co.innovaciones.bflow_server.service.ContactService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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
    value = ["/api/contacts"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@SecurityRequirement(name = "bearer-jwt")
class ContactResource(
    private val contactService: ContactService
) {
    private val logger: Logger = LoggerFactory.getLogger(ContactResource::class.java)

    @GetMapping
    fun getAllContacts(): ResponseEntity<List<ContactDTO>> {
        logger.info("GET /api/contacts called")
        return ResponseEntity.ok(contactService.findAll())
    }

    @GetMapping("/{id}")
    fun getContact(@PathVariable(name = "id") id: Long): ResponseEntity<ContactDTO> {
        logger.info("GET /api/contacts/{} called", id)
        return ResponseEntity.ok(contactService.get(id))
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createContact(@RequestBody @Valid contactDTO: ContactDTO): ResponseEntity<Long> {
        logger.info("POST /api/contacts called with contactDTO: {}", contactDTO)
        val createdId = contactService.create(contactDTO)
        logger.info("Contact created with id: {}", createdId)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateContact(@PathVariable(name = "id") id: Long, @RequestBody @Valid contactDTO: ContactDTO): ResponseEntity<Long> {
        logger.info("PUT /api/contacts/{} called with contactDTO: {}", id, contactDTO)
        contactService.update(id, contactDTO)
        logger.info("Contact with id: {} updated", id)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteContact(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        logger.info("DELETE /api/contacts/{} called", id)
        contactService.delete(id)
        logger.info("Contact with id: {} deleted", id)
        return ResponseEntity.noContent().build()
    }
}
