package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.Product
import co.innovaciones.bflow_server.model.ProductDTO
import co.innovaciones.bflow_server.repos.CategoryRepository
import co.innovaciones.bflow_server.repos.ContactRepository
import co.innovaciones.bflow_server.repos.ProductRepository
import co.innovaciones.bflow_server.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val contactRepository: ContactRepository
) {

    fun findAll(): List<ProductDTO> {
        val products = productRepository.findAll(Sort.by("id"))
        return products.stream()
            .map { product -> mapToDTO(product, ProductDTO()) }
            .toList()
    }

    fun findByCategory(categoryId: Long): List<ProductDTO> {
        val category = categoryRepository.findById(categoryId).get()
        val products = productRepository.findAllByCategory(category, Sort.by("name"))
        return products.stream()
            .map { item -> mapToDTO(item, ProductDTO()) }
            .toList()
    }

    fun `get`(id: Long): ProductDTO = productRepository.findById(id)
        .map { product -> mapToDTO(product, ProductDTO()) }
        .orElseThrow { NotFoundException() }

    fun create(productDTO: ProductDTO): Long {
        val product = Product()
        mapToEntity(productDTO, product)
        return productRepository.save(product).id!!
    }

    fun update(id: Long, productDTO: ProductDTO) {
        val product = productRepository.findById(id)
            .orElseThrow { NotFoundException() }
        mapToEntity(productDTO, product)
        productRepository.save(product)
    }

    fun delete(id: Long) {
        productRepository.deleteById(id)
    }

    private fun mapToDTO(product: Product, productDTO: ProductDTO): ProductDTO {
        productDTO.id = product.id
        productDTO.name = product.name
        productDTO.sku = product.sku
        productDTO.description = product.description
        productDTO.unitPrice = product.unitPrice
        productDTO.unitOfMeasure = product.unitOfMeasure
        productDTO.uomOrderIncrement = product.uomOrderIncrement
        productDTO.url = product.url
        productDTO.category = product.category?.id
        productDTO.supplier = product.supplier?.id
        return productDTO
    }

    private fun mapToEntity(productDTO: ProductDTO, product: Product): Product {
        product.name = productDTO.name
        product.sku = productDTO.sku
        product.description = productDTO.description
        product.unitPrice = productDTO.unitPrice
        product.unitOfMeasure = productDTO.unitOfMeasure
        product.uomOrderIncrement = productDTO.uomOrderIncrement
        product.url = productDTO.url
        val category = if (productDTO.category == null) null else
            categoryRepository.findById(productDTO.category!!)
                .orElseThrow { NotFoundException("category not found") }
        product.category = category
        val supplier = if (productDTO.supplier == null) null else
            contactRepository.findById(productDTO.supplier!!)
                .orElseThrow { NotFoundException("supplier not found") }
        product.supplier = supplier
        return product
    }

    fun skuExists(sku: String?): Boolean = productRepository.existsBySkuIgnoreCase(sku)


}
