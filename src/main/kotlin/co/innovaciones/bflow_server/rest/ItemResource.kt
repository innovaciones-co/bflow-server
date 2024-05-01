package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.ItemDTO
import co.innovaciones.bflow_server.service.CategoryService
import co.innovaciones.bflow_server.service.ItemService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import org.apache.juli.logging.Log
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
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(
    value = ["/api/items"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class ItemResource(
    private val itemService: ItemService,
    private val categoryService: CategoryService
) {

    @GetMapping
    fun getAllItems(@RequestParam jobId: Long?): ResponseEntity<List<ItemDTO>> {
        if (jobId != null) {
            return ResponseEntity.ok(itemService.findByJob(jobId))
        }
        return ResponseEntity.ok(itemService.findAll())
    }

    @GetMapping("/{id}")
    fun getItem(@PathVariable(name = "id") id: Long): ResponseEntity<ItemDTO> =
        ResponseEntity.ok(itemService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createItem(@RequestBody @Valid itemDTO: ItemDTO): ResponseEntity<Long> {
        val createdId = itemService.create(itemDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateItem(@PathVariable(name = "id") id: Long, @RequestBody @Valid itemDTO: ItemDTO):
            ResponseEntity<Long> {
        itemService.update(id, itemDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteItem(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        itemService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
