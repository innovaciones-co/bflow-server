package co.innovaciones.bflow_server.repos

import co.innovaciones.bflow_server.domain.Job
import co.innovaciones.bflow_server.domain.PurchaseOrder
import org.springframework.data.jpa.repository.JpaRepository


interface PurchaseOrderRepository : JpaRepository<PurchaseOrder, Long> {

    fun findFirstByJob(job: Job): PurchaseOrder?

    fun existsByNumberIgnoreCase(number: String?): Boolean

}
