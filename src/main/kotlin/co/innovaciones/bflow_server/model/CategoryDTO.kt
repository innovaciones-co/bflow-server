package co.innovaciones.bflow_server.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


class CategoryDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    var name: String? = null

    @NotNull
    @CategoryContactUnique
    var contact: Long? = null

    @CategoryParentCategoryUnique
    var parentCategory: Long? = null

}
