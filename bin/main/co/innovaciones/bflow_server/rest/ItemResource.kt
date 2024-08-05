package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.ItemDTO
import co.innovaciones.bflow_server.service.ItemService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["/api/items"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class ItemResource(
    private val itemService: ItemService,
) {
    private val logger: Logger = LoggerFactory.getLogger(ItemResource::class.java)

    @GetMapping
    fun getAllItems(@RequestParam jobId: Long?): ResponseEntity<List<ItemDTO>> {
        logger.info("GET /api/items called with jobId: {}", jobId)
        val response = if (jobId != null) {
            itemService.findByJob(jobId)
        } else {
            itemService.findAll()
        }
        logger.info("Retrieved {} items", response.size)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getItem(@PathVariable(name = "id") id: Long): ResponseEntity<ItemDTO> {
        logger.info("GET /api/items/{} called", id)
        val item = itemService.get(id)
        logger.info("Retrieved item: {}", item)
        return ResponseEntity.ok(item)
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createItem(@RequestBody @Valid itemDTO: ItemDTO): ResponseEntity<Long> {
        logger.info("POST /api/items called with itemDTO: {}", itemDTO)
        val createdId = itemService.create(itemDTO)
        logger.info("Item created with id: {}", createdId)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateItem(@PathVariable(name = "id") id: Long, @RequestBody @Valid itemDTO: ItemDTO): ResponseEntity<Long> {
        logger.info("PUT /api/items/{} called with itemDTO: {}", id, itemDTO)
        itemService.update(id, itemDTO)
        logger.info("Item with id: {} updated", id)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteItem(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        logger.info("DELETE /api/items/{} called", id)
        itemService.delete(id)
        logger.info("Item with id: {} deleted", id)
        return ResponseEntity.noContent().build()
    }
}
