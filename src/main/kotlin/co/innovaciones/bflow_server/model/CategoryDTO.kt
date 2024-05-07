package co.innovaciones.bflow_server.model

import co.innovaciones.bflow_server.model.validators.CategoryTradeCodeUnique
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


class CategoryDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    var name: String? = null

    @NotNull
    @CategoryTradeCodeUnique
    var tradeCode: Int? = null

    @CategoryParentCategoryUnique
    var parentCategory: Long? = null

}
