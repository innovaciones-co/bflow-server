package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.CategoryDTO
import co.innovaciones.bflow_server.service.CategoryService
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
    value = ["/api/categories"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@SecurityRequirement(name = "bearer-jwt")
class CategoryResource(
    private val categoryService: CategoryService
) {
    private val logger: Logger = LoggerFactory.getLogger(CategoryResource::class.java)

    @GetMapping
    fun getAllCategories(): ResponseEntity<List<CategoryDTO>> {
        logger.info("GET /api/categories called")
        return ResponseEntity.ok(categoryService.findAll())
    }

    @GetMapping("/{id}")
    fun getCategory(@PathVariable(name = "id") id: Long): ResponseEntity<CategoryDTO> {
        logger.info("GET /api/categories/{} called", id)
        return ResponseEntity.ok(categoryService.get(id))
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createCategory(@RequestBody @Valid categoryDTO: CategoryDTO): ResponseEntity<Long> {
        logger.info("POST /api/categories called with categoryDTO: {}", categoryDTO)
        val createdId = categoryService.create(categoryDTO)
        logger.info("Category created with id: {}", createdId)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateCategory(@PathVariable(name = "id") id: Long, @RequestBody @Valid categoryDTO: CategoryDTO): ResponseEntity<Long> {
        logger.info("PUT /api/categories/{} called with categoryDTO: {}", id, categoryDTO)
        categoryService.update(id, categoryDTO)
        logger.info("Category with id: {} updated", id)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteCategory(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        logger.info("DELETE /api/categories/{} called", id)
        categoryService.delete(id)
        logger.info("Category with id: {} deleted", id)
        return ResponseEntity.noContent().build()
    }
}
