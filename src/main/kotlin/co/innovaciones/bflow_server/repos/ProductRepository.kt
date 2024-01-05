package co.innovaciones.bflow_server.repos

import co.innovaciones.bflow_server.domain.Product
import org.springframework.data.jpa.repository.JpaRepository


interface ProductRepository : JpaRepository<Product, Long> {

    fun existsBySkuIgnoreCase(sku: String?): Boolean

}
