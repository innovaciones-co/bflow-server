package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.CategoryDTO
import co.innovaciones.bflow_server.service.CategoryService
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
    value = ["/api/categories"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@SecurityRequirement(name = "bearer-jwt")
class CategoryResource(
    private val categoryService: CategoryService
) {

    @GetMapping
    fun getAllCategories(): ResponseEntity<List<CategoryDTO>> =
            ResponseEntity.ok(categoryService.findAll())

    @GetMapping("/{id}")
    fun getCategory(@PathVariable(name = "id") id: Long): ResponseEntity<CategoryDTO> =
            ResponseEntity.ok(categoryService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createCategory(@RequestBody @Valid categoryDTO: CategoryDTO): ResponseEntity<Long> {
        val createdId = categoryService.create(categoryDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateCategory(@PathVariable(name = "id") id: Long, @RequestBody @Valid
            categoryDTO: CategoryDTO): ResponseEntity<Long> {
        categoryService.update(id, categoryDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteCategory(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        categoryService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
