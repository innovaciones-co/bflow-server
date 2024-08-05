package co.innovaciones.bflow_server.mappers

import co.innovaciones.bflow_server.domain.PurchaseOrder
import co.innovaciones.bflow_server.model.ItemDTO
import co.innovaciones.bflow_server.model.PurchaseOrderDTO
import co.innovaciones.bflow_server.repos.ContactRepository
import co.innovaciones.bflow_server.repos.ItemRepository
import co.innovaciones.bflow_server.repos.JobRepository
import co.innovaciones.bflow_server.util.NotFoundException
import org.springframework.stereotype.Component

@Component
class PurchaseOrderMapper(
    private val item: ItemRepository,
    private val itemMapper: ItemMapper,
    private val jobRepository: JobRepository,
    private val contactRepository: ContactRepository
) : Mapper<PurchaseOrder, PurchaseOrderDTO> {


    override fun mapToDTO(entity: PurchaseOrder, dto: PurchaseOrderDTO): PurchaseOrderDTO = dto.apply {
        id = entity.id
        number = entity.number
        createdDate = entity.dateCreated
        sentDate = entity.sentDate
        approvedDate = entity.approvedDate
        completedDate = entity.completedDate
        status = entity.status
        job = entity.job?.id
        supplier = entity.supplier?.id
        orderItems =
                entity.orderItems?.map { orderItem -> itemMapper.mapToDTO(orderItem, ItemDTO()) }?.toSet()
    }

    override fun mapToEntity(dto: PurchaseOrderDTO, entity: PurchaseOrder): PurchaseOrder = entity.apply {
        number = dto.number
        sentDate = dto.sentDate
        approvedDate = dto.approvedDate
        completedDate = dto.completedDate
        status = dto.status
        job = if (dto.job == null) null else jobRepository.findById(dto.job!!)
            .orElseThrow { NotFoundException("job not found") }
        supplier = if (dto.supplier == null) null else contactRepository.findById(dto.supplier!!)
            .orElseThrow { NotFoundException("Supplier not found") }
    }
}