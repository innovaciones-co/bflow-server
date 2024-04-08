package co.innovaciones.bflow_server.repos

import co.innovaciones.bflow_server.domain.Category
import co.innovaciones.bflow_server.domain.Product
import org.springframework.data.jpa.repository.JpaRepository


interface ProductRepository : JpaRepository<Product, Long> {

    fun findFirstByCategory(category: Category): Product?

    fun existsBySkuIgnoreCase(sku: String?): Boolean

}
