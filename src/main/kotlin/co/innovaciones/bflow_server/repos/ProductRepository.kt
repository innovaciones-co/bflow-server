package co.innovaciones.bflow_server.repos

import co.innovaciones.bflow_server.domain.Category
import co.innovaciones.bflow_server.domain.Product
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository


interface ProductRepository : JpaRepository<Product, Long> {

    fun findFirstByName(name: String): Product?

    fun findFirstByCategory(category: Category): Product?

    fun findAllByCategory(category: Category, sort: Sort): List<Product>

    fun existsBySkuIgnoreCase(sku: String?): Boolean

}
