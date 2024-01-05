package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.StageDTO
import co.innovaciones.bflow_server.service.StageService
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
    value = ["/api/stages"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class StageResource(
    private val stageService: StageService
) {

    @GetMapping
    fun getAllStages(): ResponseEntity<List<StageDTO>> = ResponseEntity.ok(stageService.findAll())

    @GetMapping("/{id}")
    fun getStage(@PathVariable(name = "id") id: Long): ResponseEntity<StageDTO> =
            ResponseEntity.ok(stageService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createStage(@RequestBody @Valid stageDTO: StageDTO): ResponseEntity<Long> {
        val createdId = stageService.create(stageDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateStage(@PathVariable(name = "id") id: Long, @RequestBody @Valid stageDTO: StageDTO):
            ResponseEntity<Long> {
        stageService.update(id, stageDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteStage(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        stageService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
