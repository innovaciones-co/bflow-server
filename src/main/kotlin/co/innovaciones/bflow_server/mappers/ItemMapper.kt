package co.innovaciones.bflow_server.mappers

import co.innovaciones.bflow_server.domain.Item
import co.innovaciones.bflow_server.model.ItemDTO
import co.innovaciones.bflow_server.repos.CategoryRepository
import co.innovaciones.bflow_server.repos.ContactRepository
import co.innovaciones.bflow_server.repos.JobRepository
import co.innovaciones.bflow_server.repos.PurchaseOrderRepository
import co.innovaciones.bflow_server.util.NotFoundException
import org.springframework.stereotype.Component

@Component
class ItemMapper (private val purchaseOrderRepository: PurchaseOrderRepository, private val contactRepository: ContactRepository, private val categoryRepository: CategoryRepository, private val jobRepository: JobRepository) : Mapper<Item, ItemDTO> {

    override fun mapToDTO(entity: Item, dto: ItemDTO): ItemDTO {
        dto.id = entity.id
        dto.name = entity.name
        dto.description = entity.description
        dto.unitPrice = entity.unitPrice
        dto.vat = entity.vat
        dto.price = entity.price
        dto.units = entity.units
        dto.units = entity.units
        dto.purchaseOrder = entity.purchaseOrder?.id
        dto.supplier = entity.supplier?.id
        dto.category = entity.category?.id
        dto.job = entity.job?.id
        dto.measure = entity.measure
        return dto
    }

    override fun mapToEntity(dto: ItemDTO, entity: Item): Item {
        entity.name = dto.name
        entity.description = dto.description
        entity.unitPrice = if(dto.unitPrice != null) dto.unitPrice!! else 0.0
        entity.vat = if(dto.vat != null) dto.vat!! else 0.0
        //item.price = if(itemDTO.price != null) itemDTO.price!! else 0.0
        entity.units = if(dto.units != null) dto.units!! else 0
        entity.measure = dto.measure
        val purchaseOrder = if (dto.purchaseOrder == null) null else
            purchaseOrderRepository.findById(dto.purchaseOrder!!)
                .orElseThrow { NotFoundException("purchaseOrder not found") }
        entity.purchaseOrder = purchaseOrder
        val supplier = if (dto.supplier == null) null else
            contactRepository.findById(dto.supplier!!)
                .orElseThrow { NotFoundException("supplier not found") }
        entity.supplier = supplier
        val category = if (dto.category == null) null else
            categoryRepository.findById(dto.category!!)
                .orElseThrow { NotFoundException("category not found") }
        entity.category = category
        val job = if (dto.job == null) null else jobRepository.findById(dto.job!!)
            .orElseThrow { NotFoundException("job not found") }
        entity.job = job
        return entity
    }


}