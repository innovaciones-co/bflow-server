package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.NoteDTO
import co.innovaciones.bflow_server.service.NoteService
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
    value = ["/api/notes"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@SecurityRequirement(name = "bearer-jwt")
class NoteResource(
    private val noteService: NoteService
) {
    private val logger: Logger = LoggerFactory.getLogger(NoteResource::class.java)

    @GetMapping
    fun getAllNotes(): ResponseEntity<List<NoteDTO>> {
        logger.info("GET /api/notes called")
        val notes = noteService.findAll()
        logger.info("Retrieved {} notes", notes.size)
        return ResponseEntity.ok(notes)
    }

    @GetMapping("/{id}")
    fun getNote(@PathVariable(name = "id") id: Long): ResponseEntity<NoteDTO> {
        logger.info("GET /api/notes/{} called", id)
        val note = noteService.get(id)
        logger.info("Retrieved note: {}", note)
        return ResponseEntity.ok(note)
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createNote(@RequestBody @Valid noteDTO: NoteDTO): ResponseEntity<Long> {
        logger.info("POST /api/notes called with noteDTO: {}", noteDTO)
        val createdId = noteService.create(noteDTO)
        logger.info("Note created with id: {}", createdId)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateNote(@PathVariable(name = "id") id: Long, @RequestBody @Valid noteDTO: NoteDTO): ResponseEntity<Long> {
        logger.info("PUT /api/notes/{} called with noteDTO: {}", id, noteDTO)
        noteService.update(id, noteDTO)
        logger.info("Note with id: {} updated", id)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteNote(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        logger.info("DELETE /api/notes/{} called", id)
        noteService.delete(id)
        logger.info("Note with id: {} deleted", id)
        return ResponseEntity.noContent().build()
    }
}
