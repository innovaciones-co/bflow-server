package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.Item
import co.innovaciones.bflow_server.mappers.ItemMapper
import co.innovaciones.bflow_server.model.ItemDTO
import co.innovaciones.bflow_server.repos.CategoryRepository
import co.innovaciones.bflow_server.repos.ContactRepository
import co.innovaciones.bflow_server.repos.ItemRepository
import co.innovaciones.bflow_server.repos.JobRepository
import co.innovaciones.bflow_server.repos.PurchaseOrderRepository
import co.innovaciones.bflow_server.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class ItemService(
    private val itemRepository: ItemRepository,
    private val itemMapper: ItemMapper,
    private val jobRepository: JobRepository
) {

    fun findAll(): List<ItemDTO> {
        val items = itemRepository.findAll(Sort.by("id"))
        return items.stream()
                .map { item -> itemMapper.mapToDTO(item, ItemDTO()) }
                .toList()
    }

    fun findByJob(jobId: Long): List<ItemDTO> {
        val job = jobRepository.findById(jobId).get()
        val items = itemRepository.findAllByJob(job, Sort.by("name"))
        return items.stream()
            .map { item -> itemMapper.mapToDTO(item, ItemDTO()) }
            .toList()
    }

    fun `get`(id: Long): ItemDTO = itemRepository.findById(id)
            .map { item -> itemMapper. mapToDTO(item, ItemDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(itemDTO: ItemDTO): Long {
        val item = Item()
        itemMapper.mapToEntity(itemDTO, item)
        return itemRepository.save(item).id!!
    }

    fun createAll(itemsDTO: List<ItemDTO>): List<Long?> {
        val items = itemsDTO.map { itemMapper.mapToEntity(it, Item()) }

        return itemRepository.saveAll(items).map { it.id!! }
    }

    fun update(id: Long, itemDTO: ItemDTO) {
        val item = itemRepository.findById(id)
                .orElseThrow { NotFoundException() }
        itemMapper.mapToEntity(itemDTO, item)
        itemRepository.save(item)
    }

    fun delete(id: Long) {
        itemRepository.deleteById(id)
    }

    fun supplierExists(id: Long?): Boolean = itemRepository.existsBySupplierId(id)

    fun categoryExists(id: Long?): Boolean = itemRepository.existsByCategoryId(id)

}
