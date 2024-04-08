package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.PurchaseOrder
import co.innovaciones.bflow_server.model.PurchaseOrderDTO
import co.innovaciones.bflow_server.repos.ItemRepository
import co.innovaciones.bflow_server.repos.JobRepository
import co.innovaciones.bflow_server.repos.PurchaseOrderRepository
import co.innovaciones.bflow_server.util.NotFoundException
import co.innovaciones.bflow_server.util.ReferencedWarning
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class PurchaseOrderService(
    private val purchaseOrderRepository: PurchaseOrderRepository,
    private val jobRepository: JobRepository,
    private val itemRepository: ItemRepository
) {

    fun findAll(): List<PurchaseOrderDTO> {
        val purchaseOrders = purchaseOrderRepository.findAll(Sort.by("id"))
        return purchaseOrders.stream()
                .map { purchaseOrder -> mapToDTO(purchaseOrder, PurchaseOrderDTO()) }
                .toList()
    }

    fun `get`(id: Long): PurchaseOrderDTO = purchaseOrderRepository.findById(id)
            .map { purchaseOrder -> mapToDTO(purchaseOrder, PurchaseOrderDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(purchaseOrderDTO: PurchaseOrderDTO): Long {
        val purchaseOrder = PurchaseOrder()
        mapToEntity(purchaseOrderDTO, purchaseOrder)
        return purchaseOrderRepository.save(purchaseOrder).id!!
    }

    fun update(id: Long, purchaseOrderDTO: PurchaseOrderDTO) {
        val purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(purchaseOrderDTO, purchaseOrder)
        purchaseOrderRepository.save(purchaseOrder)
    }

    fun delete(id: Long) {
        purchaseOrderRepository.deleteById(id)
    }

    fun mapToDTO(purchaseOrder: PurchaseOrder, purchaseOrderDTO: PurchaseOrderDTO):
            PurchaseOrderDTO {
        purchaseOrderDTO.id = purchaseOrder.id
        purchaseOrderDTO.number = purchaseOrder.number
        purchaseOrderDTO.sentDate = purchaseOrder.sentDate
        purchaseOrderDTO.approvedDate = purchaseOrder.approvedDate
        purchaseOrderDTO.completedDate = purchaseOrder.completedDate
        purchaseOrderDTO.status = purchaseOrder.status
        purchaseOrderDTO.job = purchaseOrder.job?.id
        return purchaseOrderDTO
    }

    private fun mapToEntity(purchaseOrderDTO: PurchaseOrderDTO, purchaseOrder: PurchaseOrder):
            PurchaseOrder {
        purchaseOrder.number = purchaseOrderDTO.number
        purchaseOrder.sentDate = purchaseOrderDTO.sentDate
        purchaseOrder.approvedDate = purchaseOrderDTO.approvedDate
        purchaseOrder.completedDate = purchaseOrderDTO.completedDate
        purchaseOrder.status = purchaseOrderDTO.status
        val job = if (purchaseOrderDTO.job == null) null else
                jobRepository.findById(purchaseOrderDTO.job!!)
                .orElseThrow { NotFoundException("job not found") }
        purchaseOrder.job = job
        return purchaseOrder
    }

    fun numberExists(number: String?): Boolean =
            purchaseOrderRepository.existsByNumberIgnoreCase(number)

    fun getReferencedWarning(id: Long): ReferencedWarning? {
        val referencedWarning = ReferencedWarning()
        val purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow { NotFoundException() }
        val purchaseOrderItem = itemRepository.findFirstByPurchaseOrder(purchaseOrder)
        if (purchaseOrderItem != null) {
            referencedWarning.key = "purchaseOrder.item.purchaseOrder.referenced"
            referencedWarning.addParam(purchaseOrderItem.id)
            return referencedWarning
        }
        return null
    }

}
