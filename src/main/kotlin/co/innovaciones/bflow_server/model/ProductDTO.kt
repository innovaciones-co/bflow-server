package co.innovaciones.bflow_server.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


class ProductDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    var name: String? = null

    @NotNull
    @Size(max = 255)
    var sku: String? = null

    @Size(max = 255)
    var description: String? = null

    @NotNull
    var unitPrice: Double? = null

    @NotNull
    var unitOfMeasure: Units? = null

    var uomOrderIncrement: Double? = null

    @Size(max = 255)
    var url: String? = null

    var category: Long? = null

}
