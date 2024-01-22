package co.innovaciones.bflow_server.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

class RecoveryDTO {
    @NotNull
    @Size(max = 255)
    val username: String? = null;
}