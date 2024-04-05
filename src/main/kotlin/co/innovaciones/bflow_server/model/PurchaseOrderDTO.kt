package co.innovaciones.bflow_server.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDateTime


class PurchaseOrderDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    @PurchaseOrderNumberUnique
    var number: String? = null

    var sentDate: LocalDateTime? = null

    var approvedDate: LocalDateTime? = null

    var completedDate: LocalDateTime? = null

    @NotNull
    var status: OrderStatus? = null

    var job: Long? = null

}
