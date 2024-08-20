package co.innovaciones.bflow_server.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


class ContactDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    var name: String? = null

    @Size(max = 255)
    var idNumber: String? = null

    @NotNull
    @Size(max = 255)
    var address: String? = null

    @NotNull
    @Size(max = 255)
    var phone: String? = null

    @NotNull
    @Size(max = 255)
    @ContactEmailUnique
    var email: String? = null

    @NotNull
    var type: ContactType? = null

    @Size(max = 255)
    var accountNumber: String? = null

    @Size(max = 255)
    var accountHolderName: String? = null

    @Size(max = 255)
    var bankName: String? = null

    @Size(max = 255)
    var taxNumber: String? = null

    var details: String? = null

}
