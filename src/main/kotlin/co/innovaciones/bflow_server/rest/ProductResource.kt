package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.ProductDTO
import co.innovaciones.bflow_server.service.ProductService
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
    value = ["/api/products"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class ProductResource(
    private val productService: ProductService
) {

    @GetMapping
    fun getAllProducts(): ResponseEntity<List<ProductDTO>> =
            ResponseEntity.ok(productService.findAll())

    @GetMapping("/{id}")
    fun getProduct(@PathVariable(name = "id") id: Long): ResponseEntity<ProductDTO> =
            ResponseEntity.ok(productService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createProduct(@RequestBody @Valid productDTO: ProductDTO): ResponseEntity<Long> {
        val createdId = productService.create(productDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable(name = "id") id: Long, @RequestBody @Valid
            productDTO: ProductDTO): ResponseEntity<Long> {
        productService.update(id, productDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteProduct(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        productService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
