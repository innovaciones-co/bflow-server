package co.innovaciones.bflow_server.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


class TemplateDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    @TemplateNameUnique
    var name: String? = null

    @NotNull
    var template: String? = null

}
