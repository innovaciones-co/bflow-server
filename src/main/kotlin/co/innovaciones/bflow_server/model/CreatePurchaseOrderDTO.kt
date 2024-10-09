package co.innovaciones.bflow_server.model

import jakarta.validation.constraints.NotNull

@ItemsInJob
class CreatePurchaseOrderDTO {
    @NotNull
    var job : Long? = null

    @NotNull
    var items : List<ItemDTO>? = null
}