package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.ProductDTO
import co.innovaciones.bflow_server.model.validators.OnCreate
import co.innovaciones.bflow_server.model.validators.OnUpdate
import co.innovaciones.bflow_server.service.ProductService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import java.lang.Void
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
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
    value = ["/api/products"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@SecurityRequirement(name = "bearer-jwt")
@SecurityRequirement(name = "bearer-jwt")
class ProductResource(
    private val productService: ProductService
) {

    @GetMapping
    fun getAllProducts(@RequestParam categoryId: Long?, @RequestParam supplierId: Long?): ResponseEntity<List<ProductDTO>> {

        if (categoryId != null && supplierId != null) return ResponseEntity.ok(productService.findBySupplierAndCategory(supplierId, categoryId))

        if (categoryId != null) return ResponseEntity.ok(productService.findByCategory(categoryId))

        if (supplierId != null) return ResponseEntity.ok(productService.findBySupplier(supplierId))

        return ResponseEntity.ok(productService.findAll())
    }


    @GetMapping("/{id}")
    fun getProduct(@PathVariable(name = "id") id: Long): ResponseEntity<ProductDTO> =
            ResponseEntity.ok(productService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createProduct(@RequestBody @Validated(OnCreate::class) productDTO: ProductDTO): ResponseEntity<Long> {
        val createdId = productService.create(productDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable(name = "id") id: Long, @RequestBody @Validated(OnUpdate::class)
            productDTO: ProductDTO): ResponseEntity<Long> {
        productService.update(id, productDTO)
        return ResponseEntity.ok(id)
    }

    @PutMapping("/upsert")
    fun upsertProducts(@RequestBody @Validated(OnUpdate::class) productsListDTO: List<ProductDTO>): ResponseEntity<List<Long>> {
        val updated = productService.upsertAll(productsListDTO)
        return ResponseEntity.ok(updated)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteProduct(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        productService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
