package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.OrderItemDTO
import co.innovaciones.bflow_server.service.OrderItemService
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
    value = ["/api/orderItems"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class OrderItemResource(
    private val orderItemService: OrderItemService
) {

    @GetMapping
    fun getAllOrderItems(): ResponseEntity<List<OrderItemDTO>> =
            ResponseEntity.ok(orderItemService.findAll())

    @GetMapping("/{id}")
    fun getOrderItem(@PathVariable(name = "id") id: Long): ResponseEntity<OrderItemDTO> =
            ResponseEntity.ok(orderItemService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createOrderItem(@RequestBody @Valid orderItemDTO: OrderItemDTO): ResponseEntity<Long> {
        val createdId = orderItemService.create(orderItemDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateOrderItem(@PathVariable(name = "id") id: Long, @RequestBody @Valid
            orderItemDTO: OrderItemDTO): ResponseEntity<Long> {
        orderItemService.update(id, orderItemDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteOrderItem(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        orderItemService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
