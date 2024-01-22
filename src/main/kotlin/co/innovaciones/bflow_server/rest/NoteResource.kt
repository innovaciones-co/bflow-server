package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.NoteDTO
import co.innovaciones.bflow_server.service.NoteService
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
    value = ["/api/notes"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@SecurityRequirement(name = "bearer-jwt")
@SecurityRequirement(name = "bearer-jwt")
class NoteResource(
    private val noteService: NoteService
) {

    @GetMapping
    fun getAllNotes(): ResponseEntity<List<NoteDTO>> = ResponseEntity.ok(noteService.findAll())

    @GetMapping("/{id}")
    fun getNote(@PathVariable(name = "id") id: Long): ResponseEntity<NoteDTO> =
            ResponseEntity.ok(noteService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createNote(@RequestBody @Valid noteDTO: NoteDTO): ResponseEntity<Long> {
        val createdId = noteService.create(noteDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateNote(@PathVariable(name = "id") id: Long, @RequestBody @Valid noteDTO: NoteDTO):
            ResponseEntity<Long> {
        noteService.update(id, noteDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteNote(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        noteService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
