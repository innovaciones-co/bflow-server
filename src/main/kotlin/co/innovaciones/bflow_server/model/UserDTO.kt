package co.innovaciones.bflow_server.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.OffsetDateTime


class UserDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    var firstName: String? = null

    @NotNull
    @Size(max = 255)
    var lastName: String? = null

    @NotNull
    @Size(max = 255)
    @UserUsernameUnique
    var username: String? = null

    @NotNull
    @Size(max = 255)
    var password: String? = null

    @NotNull
    @Size(max = 255)
    @UserEmailUnique
    var email: String? = null

    @NotNull
    var role: UserRole? = null

    @Size(max = 255)
    var recoveryToken: String? = null

    var tokenExpirationDate: OffsetDateTime? = null

    val fullName: String
        get() = "${firstName.orEmpty()} ${lastName.orEmpty()}".trim()

}
