package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.ProductDTO
import co.innovaciones.bflow_server.model.validators.OnCreate
import co.innovaciones.bflow_server.model.validators.OnUpdate
import co.innovaciones.bflow_server.service.ProductService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["/api/products"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@SecurityRequirement(name = "bearer-jwt")
class ProductResource(
    private val productService: ProductService
) {
    private val logger: Logger = LoggerFactory.getLogger(ProductResource::class.java)

    @GetMapping
    fun getAllProducts(
        @RequestParam categoryId: Long?,
        @RequestParam supplierId: Long?
    ): ResponseEntity<List<ProductDTO>> {
        logger.info("GET /api/products called with categoryId: {} and supplierId: {}", categoryId, supplierId)

        val products = when {
            categoryId != null && supplierId != null -> productService.findBySupplierAndCategory(supplierId, categoryId)
            categoryId != null -> productService.findByCategory(categoryId)
            supplierId != null -> productService.findBySupplier(supplierId)
            else -> productService.findAll()
        }

        logger.info("Retrieved {} products", products.size)
        return ResponseEntity.ok(products)
    }

    @GetMapping("/{id}")
    fun getProduct(@PathVariable(name = "id") id: Long): ResponseEntity<ProductDTO> {
        logger.info("GET /api/products/{} called", id)
        val product = productService.get(id)
        logger.info("Retrieved product: {}", product)
        return ResponseEntity.ok(product)
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createProduct(@RequestBody @Validated(OnCreate::class) productDTO: ProductDTO): ResponseEntity<Long> {
        logger.info("POST /api/products called with productDTO: {}", productDTO)
        val createdId = productService.create(productDTO)
        logger.info("Product created with id: {}", createdId)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable(name = "id") id: Long,
        @RequestBody @Validated(OnUpdate::class) productDTO: ProductDTO
    ): ResponseEntity<Long> {
        logger.info("PUT /api/products/{} called with productDTO: {}", id, productDTO)
        productService.update(id, productDTO)
        logger.info("Product with id: {} updated", id)
        return ResponseEntity.ok(id)
    }

    @PutMapping("/upsert")
    fun upsertProducts(
        @RequestBody @Validated(OnUpdate::class) productsListDTO: List<ProductDTO>
    ): ResponseEntity<List<Long>> {
        logger.info("PUT /api/products/upsert called with productsListDTO: {}", productsListDTO)
        val updatedIds = productService.upsertAll(productsListDTO)
        logger.info("Products upserted: {}", updatedIds)
        return ResponseEntity.ok(updatedIds)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteProduct(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        logger.info("DELETE /api/products/{} called", id)
        productService.delete(id)
        logger.info("Product with id: {} deleted", id)
        return ResponseEntity.noContent().build()
    }
}
