package co.innovaciones.bflow_server.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDateTime
import java.time.OffsetDateTime


class PurchaseOrderDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    @PurchaseOrderNumberUnique
    var number: String? = null

    var sentDate: LocalDateTime? = null

    var approvedDate: LocalDateTime? = null

    var completedDate: LocalDateTime? = null

    var createdDate: OffsetDateTime? = null

    var orderItems: Set<ItemDTO>? = null

    @NotNull
    var status: OrderStatus? = null

    @NotNull
    var job: Long? = null

    @NotNull
    var supplier: Long? = null

}
