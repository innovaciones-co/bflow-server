package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.Category
import co.innovaciones.bflow_server.model.CategoryDTO
import co.innovaciones.bflow_server.repos.CategoryRepository
import co.innovaciones.bflow_server.repos.ItemRepository
import co.innovaciones.bflow_server.repos.ProductRepository
import co.innovaciones.bflow_server.util.NotFoundException
import co.innovaciones.bflow_server.util.ReferencedWarning
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
    private val productRepository: ProductRepository,
    private val itemRepository: ItemRepository
) {

    fun findAll(): List<CategoryDTO> {
        val categories = categoryRepository.findAll(Sort.by("id"))
        return categories.stream()
                .map { category -> mapToDTO(category, CategoryDTO()) }
                .toList()
    }

    fun findAllById(ids: List<Long>): List<CategoryDTO> {
        val categories = categoryRepository.findAllByIdIn(ids, Sort.by("name"))
        return categories.stream()
            .map { category -> mapToDTO(category, CategoryDTO()) }
            .toList()
    }

    fun `get`(id: Long): CategoryDTO = categoryRepository.findById(id)
            .map { category -> mapToDTO(category, CategoryDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(categoryDTO: CategoryDTO): Long {
        val category = Category()
        mapToEntity(categoryDTO, category)
        return categoryRepository.save(category).id!!
    }

    fun update(id: Long, categoryDTO: CategoryDTO) {
        val category = categoryRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(categoryDTO, category)
        categoryRepository.save(category)
    }

    fun delete(id: Long) {
        categoryRepository.deleteById(id)
    }

    private fun mapToDTO(category: Category, categoryDTO: CategoryDTO): CategoryDTO {
        categoryDTO.id = category.id
        categoryDTO.name = category.name
        categoryDTO.parentCategory = category.parentCategory?.id
        categoryDTO.tradeCode = category.tradeCode
        return categoryDTO
    }

    private fun mapToEntity(categoryDTO: CategoryDTO, category: Category): Category {
        category.name = categoryDTO.name
        category.tradeCode = categoryDTO.tradeCode
        val parentCategory = if (categoryDTO.parentCategory == null) null else
                categoryRepository.findById(categoryDTO.parentCategory!!)
                .orElseThrow { NotFoundException("parentCategory not found") }
        category.parentCategory = parentCategory
        return category
    }

    fun nameExists(name: String?): Boolean = categoryRepository.existsByNameIgnoreCase(name)

    fun tradeCodeExists(tradeCode: Int?): Boolean = categoryRepository.existsByTradeCode(tradeCode)

    fun parentCategoryExists(id: Long?): Boolean = categoryRepository.existsByParentCategoryId(id)

    fun getReferencedWarning(id: Long): ReferencedWarning? {
        val referencedWarning = ReferencedWarning()
        val category = categoryRepository.findById(id)
                .orElseThrow { NotFoundException() }
        val categoryProduct = productRepository.findFirstByCategory(category)
        if (categoryProduct != null) {
            referencedWarning.key = "category.product.category.referenced"
            referencedWarning.addParam(categoryProduct.id)
            return referencedWarning
        }
        val parentCategoryCategory = categoryRepository.findFirstByParentCategoryAndIdNot(category,
                category.id)
        if (parentCategoryCategory != null) {
            referencedWarning.key = "category.category.parentCategory.referenced"
            referencedWarning.addParam(parentCategoryCategory.id)
            return referencedWarning
        }
        val categoryItem = itemRepository.findFirstByCategory(category)
        if (categoryItem != null) {
            referencedWarning.key = "category.item.category.referenced"
            referencedWarning.addParam(categoryItem.id)
            return referencedWarning
        }
        return null
    }

}
