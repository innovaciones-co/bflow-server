package co.innovaciones.bflow_server.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


class ItemDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    var name: String? = null

    var description: String? = null

    @NotNull
    var unitPrice: Double? = null

    var vat: Double? = null

    var price: Double? = null

    var purchaseOrder: Long? = null

    @NotNull
    @ItemSupplierUnique
    var supplier: Long? = null

    @NotNull
    @ItemCategoryUnique
    var category: Long? = null

    var job: Long? = null

}
