package co.innovaciones.bflow_server.repos

import co.innovaciones.bflow_server.domain.Category
import co.innovaciones.bflow_server.domain.Contact
import co.innovaciones.bflow_server.domain.Item
import co.innovaciones.bflow_server.domain.Job
import co.innovaciones.bflow_server.domain.PurchaseOrder
import org.springframework.data.jpa.repository.JpaRepository


interface ItemRepository : JpaRepository<Item, Long> {

    fun findFirstByPurchaseOrder(purchaseOrder: PurchaseOrder): Item?

    fun findFirstBySupplier(contact: Contact): Item?

    fun findFirstByCategory(category: Category): Item?

    fun findFirstByJob(job: Job): Item?

    fun existsBySupplierId(id: Long?): Boolean

    fun existsByCategoryId(id: Long?): Boolean

}
