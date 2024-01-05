package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.OrderItem
import co.innovaciones.bflow_server.model.OrderItemDTO
import co.innovaciones.bflow_server.repos.OrderItemRepository
import co.innovaciones.bflow_server.repos.PurchaseOrderRepository
import co.innovaciones.bflow_server.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class OrderItemService(
    private val orderItemRepository: OrderItemRepository,
    private val purchaseOrderRepository: PurchaseOrderRepository
) {

    fun findAll(): List<OrderItemDTO> {
        val orderItems = orderItemRepository.findAll(Sort.by("id"))
        return orderItems.stream()
                .map { orderItem -> mapToDTO(orderItem, OrderItemDTO()) }
                .toList()
    }

    fun `get`(id: Long): OrderItemDTO = orderItemRepository.findById(id)
            .map { orderItem -> mapToDTO(orderItem, OrderItemDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(orderItemDTO: OrderItemDTO): Long {
        val orderItem = OrderItem()
        mapToEntity(orderItemDTO, orderItem)
        return orderItemRepository.save(orderItem).id!!
    }

    fun update(id: Long, orderItemDTO: OrderItemDTO) {
        val orderItem = orderItemRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(orderItemDTO, orderItem)
        orderItemRepository.save(orderItem)
    }

    fun delete(id: Long) {
        orderItemRepository.deleteById(id)
    }

    private fun mapToDTO(orderItem: OrderItem, orderItemDTO: OrderItemDTO): OrderItemDTO {
        orderItemDTO.id = orderItem.id
        orderItemDTO.name = orderItem.name
        orderItemDTO.description = orderItem.description
        orderItemDTO.unitPrice = orderItem.unitPrice
        orderItemDTO.vat = orderItem.vat
        orderItemDTO.price = orderItem.price
        orderItemDTO.purchaseOrder = orderItem.purchaseOrder?.id
        return orderItemDTO
    }

    private fun mapToEntity(orderItemDTO: OrderItemDTO, orderItem: OrderItem): OrderItem {
        orderItem.name = orderItemDTO.name
        orderItem.description = orderItemDTO.description
        orderItem.unitPrice = orderItemDTO.unitPrice
        orderItem.vat = orderItemDTO.vat
        orderItem.price = orderItemDTO.price
        val purchaseOrder = if (orderItemDTO.purchaseOrder == null) null else
                purchaseOrderRepository.findById(orderItemDTO.purchaseOrder!!)
                .orElseThrow { NotFoundException("purchaseOrder not found") }
        orderItem.purchaseOrder = purchaseOrder
        return orderItem
    }

}
