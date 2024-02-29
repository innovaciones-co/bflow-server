package co.innovaciones.bflow_server.model

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


class ContactDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    var name: String? = null

    @NotNull
    @Size(max = 255)
    var address: String? = null

    @NotNull
    @Size(max = 255)
    @Email
    var email: String? = null

    @NotNull
    var type: ContactType? = null

}
