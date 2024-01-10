package co.innovaciones.bflow_server.model

import jakarta.validation.constraints.*


class UserDTO {

    var id: Long? = null

    @NotNull(message = "El primer nombre no puede ir vacio")
    @Size(max = 255, message = "First name length must be less than or equal to 255")
    var firstName: String? = null

    @NotNull(message = "Last name must not be null")
    @Size(max = 255, message = "Last name length must be less than or equal to 255")
    var lastName: String? = null

    @NotNull(message = "Username must not be null")
    @Size(max = 255, message = "Username length must be less than or equal to 255")
    var username: String? = null

    @NotNull
    @Size(max = 255)
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[.!@#$&*])(?=\\S+$).{8,}$",
        message = "Password must be at least 8 characters long and contain at least one uppercase letter and one special character"
    )
    var password: String? = null

    @Size(max = 255, message = "Email length must be less than or equal to 255")
    @Email(message = "Email should be valid")
    var email: String? = null

}





