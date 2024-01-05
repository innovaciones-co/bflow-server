package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.Category
import co.innovaciones.bflow_server.model.CategoryDTO
import co.innovaciones.bflow_server.repos.CategoryRepository
import co.innovaciones.bflow_server.repos.ContactRepository
import co.innovaciones.bflow_server.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
    private val contactRepository: ContactRepository
) {

    fun findAll(): List<CategoryDTO> {
        val categories = categoryRepository.findAll(Sort.by("id"))
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
        categoryDTO.contact = category.contact?.id
        categoryDTO.parentCategory = category.parentCategory?.id
        return categoryDTO
    }

    private fun mapToEntity(categoryDTO: CategoryDTO, category: Category): Category {
        category.name = categoryDTO.name
        val contact = if (categoryDTO.contact == null) null else
                contactRepository.findById(categoryDTO.contact!!)
                .orElseThrow { NotFoundException("contact not found") }
        category.contact = contact
        val parentCategory = if (categoryDTO.parentCategory == null) null else
                categoryRepository.findById(categoryDTO.parentCategory!!)
                .orElseThrow { NotFoundException("parentCategory not found") }
        category.parentCategory = parentCategory
        return category
    }

}
