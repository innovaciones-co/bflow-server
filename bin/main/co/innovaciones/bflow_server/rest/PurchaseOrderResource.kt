package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.CreatePurchaseOrderDTO
import co.innovaciones.bflow_server.model.PurchaseOrderDTO
import co.innovaciones.bflow_server.service.PurchaseOrderService
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
    value = ["/api/purchaseOrders"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@SecurityRequirement(name = "bearer-jwt")
class PurchaseOrderResource(
    private val purchaseOrderService: PurchaseOrderService
) {
    private val logger: Logger = LoggerFactory.getLogger(PurchaseOrderResource::class.java)

    @GetMapping
    fun getAllPurchaseOrders(@RequestParam jobId: Long?): ResponseEntity<List<PurchaseOrderDTO>> {
        logger.info("GET /api/purchaseOrders called with jobId: {}", jobId)
        val purchaseOrders = if (jobId != null) {
            purchaseOrderService.findByJob(jobId)
        } else {
            purchaseOrderService.findAll()
        }
        logger.info("Retrieved {} purchase orders", purchaseOrders.size)
        return ResponseEntity.ok(purchaseOrders)
    }

    @GetMapping("/{id}")
    fun getPurchaseOrder(@PathVariable(name = "id") id: Long): ResponseEntity<PurchaseOrderDTO> {
        logger.info("GET /api/purchaseOrders/{} called", id)
        val purchaseOrder = purchaseOrderService.get(id)
        logger.info("Retrieved purchase order: {}", purchaseOrder)
        return ResponseEntity.ok(purchaseOrder)
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createPurchaseOrder(@RequestBody @Valid purchaseOrderDTO: PurchaseOrderDTO): ResponseEntity<Long> {
        logger.info("POST /api/purchaseOrders called with purchaseOrderDTO: {}", purchaseOrderDTO)
        val createdId = purchaseOrderService.create(purchaseOrderDTO)
        logger.info("Purchase order created with id: {}", createdId)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PostMapping("/items")
    @ApiResponse(responseCode = "201")
    fun createPurchaseOrder(@RequestBody @Valid createPurchaseOrderDTO: CreatePurchaseOrderDTO): ResponseEntity<List<Long>> {
        logger.info("POST /api/purchaseOrders/items called with createPurchaseOrderDTO: {}", createPurchaseOrderDTO)
        val orderIds = purchaseOrderService.createFromItems(createPurchaseOrderDTO)
        logger.info("Purchase orders created with ids: {}", orderIds)
        return ResponseEntity(orderIds, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updatePurchaseOrder(@PathVariable(name = "id") id: Long, @RequestBody @Valid purchaseOrderDTO: PurchaseOrderDTO): ResponseEntity<Long> {
        logger.info("PUT /api/purchaseOrders/{} called with purchaseOrderDTO: {}", id, purchaseOrderDTO)
        purchaseOrderService.update(id, purchaseOrderDTO)
        logger.info("Purchase order with id: {} updated", id)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deletePurchaseOrder(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        logger.info("DELETE /api/purchaseOrders/{} called", id)
        purchaseOrderService.delete(id)
        logger.info("Purchase order with id: {} deleted", id)
        return ResponseEntity.noContent().build()
    }
}
