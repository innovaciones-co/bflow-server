package co.innovaciones.bflow_server.model

import co.innovaciones.bflow_server.model.validators.UniqueJobNumber
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate


class JobWriteDTO : JobDTO() {

    @NotNull
    var client: Long? = null

    @NotNull
    var user: Long? = null

    @NotNull
    var supervisor: Long? = null

    var notes: Set<Long>? = null

    var files: Set<Long>? = null

}
