package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.PurchaseOrderDTO
import co.innovaciones.bflow_server.service.PurchaseOrderService
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
    value = ["/api/purchaseOrders"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class PurchaseOrderResource(
    private val purchaseOrderService: PurchaseOrderService
) {

    @GetMapping
    fun getAllPurchaseOrders(): ResponseEntity<List<PurchaseOrderDTO>> =
            ResponseEntity.ok(purchaseOrderService.findAll())

    @GetMapping("/{id}")
    fun getPurchaseOrder(@PathVariable(name = "id") id: Long): ResponseEntity<PurchaseOrderDTO> =
            ResponseEntity.ok(purchaseOrderService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createPurchaseOrder(@RequestBody @Valid purchaseOrderDTO: PurchaseOrderDTO):
            ResponseEntity<Long> {
        val createdId = purchaseOrderService.create(purchaseOrderDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updatePurchaseOrder(@PathVariable(name = "id") id: Long, @RequestBody @Valid
            purchaseOrderDTO: PurchaseOrderDTO): ResponseEntity<Long> {
        purchaseOrderService.update(id, purchaseOrderDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deletePurchaseOrder(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        purchaseOrderService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
