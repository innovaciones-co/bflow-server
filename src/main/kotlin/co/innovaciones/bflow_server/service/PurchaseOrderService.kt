package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.PurchaseOrder
import co.innovaciones.bflow_server.mappers.PurchaseOrderMapper
import co.innovaciones.bflow_server.model.CreatePurchaseOrderDTO
import co.innovaciones.bflow_server.model.ItemDTO
import co.innovaciones.bflow_server.model.OrderStatus
import co.innovaciones.bflow_server.model.PurchaseOrderDTO
import co.innovaciones.bflow_server.repos.ContactRepository
import co.innovaciones.bflow_server.repos.ItemRepository
import co.innovaciones.bflow_server.repos.JobRepository
import co.innovaciones.bflow_server.repos.PurchaseOrderRepository
import co.innovaciones.bflow_server.util.NotFoundException
import co.innovaciones.bflow_server.util.ReferencedWarning
import jakarta.transaction.Transactional
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*


@Service
@Transactional
class PurchaseOrderService(
    private val purchaseOrderRepository: PurchaseOrderRepository,
    private val jobRepository: JobRepository,
    private val itemRepository: ItemRepository,
    private val itemService: ItemService,
    private val contactRepository: ContactRepository,
    private val purchaseOrderMapper: PurchaseOrderMapper,
) {

    fun findAll(): List<PurchaseOrderDTO> {
        val purchaseOrders = purchaseOrderRepository.findAll(Sort.by("id"))
        return purchaseOrders.stream()
                .map { purchaseOrder -> purchaseOrderMapper.mapToDTO(purchaseOrder, PurchaseOrderDTO()) }
                .toList()
    }

    fun findByJob(jobId: Long): List<PurchaseOrderDTO> {
        val job = jobRepository.findById(jobId).get()
        val purchaseOrders = purchaseOrderRepository.findByJob(job)
        return purchaseOrders.stream()
            .map { item -> purchaseOrderMapper.mapToDTO(item, PurchaseOrderDTO()) }
            .toList() as List<PurchaseOrderDTO>
    }

    fun `get`(id: Long): PurchaseOrderDTO = purchaseOrderRepository.findById(id)
            .map { purchaseOrder -> purchaseOrderMapper.mapToDTO(purchaseOrder, PurchaseOrderDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(purchaseOrderDTO: PurchaseOrderDTO): Long {
        val purchaseOrder = PurchaseOrder()
        mapToEntity(purchaseOrderDTO, purchaseOrder)
        return purchaseOrderRepository.save(purchaseOrder).id!!
    }

    fun createFromItems(createPurchaseOrderDTO: CreatePurchaseOrderDTO): List<Long> {
        val orderIds = mutableListOf<Long>()

        val itemsBySupplier = createPurchaseOrderDTO.items!!.groupBy { it.supplier }

        itemsBySupplier.forEach { (supplier, items) ->
            val purchaseOrder = PurchaseOrder()
            mapToEntity(createPurchaseOrderDTO, purchaseOrder)

            // Set supplier for the purchase order
            if (supplier != null)
                purchaseOrder.supplier = contactRepository.findById(supplier).get()

            // Save purchase order
            val orderId = purchaseOrderRepository.save(purchaseOrder).id!!
            orderIds.add(orderId)

            // Update items with the purchase order ID
            items.forEach { item ->
                item.purchaseOrder = orderId
                itemService.update(item.id!!, item)
            }
        }

        return orderIds
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

    private fun mapToEntity(createPurchaseOrderDTO: CreatePurchaseOrderDTO, purchaseOrder: PurchaseOrder):
            PurchaseOrder {
        purchaseOrder.number = generateOrderNumber("PO")
        purchaseOrder.status = OrderStatus.DRAFT
        val job = if (createPurchaseOrderDTO.job == null) null else
            jobRepository.findById(createPurchaseOrderDTO.job!!)
                .orElseThrow { NotFoundException("job not found") }
        purchaseOrder.job = job

        return purchaseOrder
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
        val supplier = if (purchaseOrderDTO.supplier == null) null else
            contactRepository.findById(purchaseOrderDTO.supplier!!)
                .orElseThrow { NotFoundException("Supplier not found") }
        purchaseOrder.supplier = supplier
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

    fun generateOrderNumber(prefix: String): String {
        val lastOrder = purchaseOrderRepository.findTopByOrderByIdDesc()
        var orderNumber : Long = 1
        if (lastOrder?.id != null) {
            orderNumber = lastOrder.id!! + 1
        }
        return "$prefix${orderNumber.toString().padStart(4, '0')}"

    }

    fun findAllByJob(jobId: Long): List<PurchaseOrderDTO>? {
        val job = jobRepository.findById(jobId).get()
        val purchaseOrders = purchaseOrderRepository.findByJob(job)
        return purchaseOrders.stream()
            .map { purchaseOrder -> purchaseOrderMapper.mapToDTO(purchaseOrder, PurchaseOrderDTO()) }
            .toList()
    }

}
