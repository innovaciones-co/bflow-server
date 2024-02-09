package co.innovaciones.bflow_server.model

import co.innovaciones.bflow_server.model.validators.UniqueJobNumber
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate


class JobReadDTO : JobDTO() {

    var client: ContactDTO? = null

    var user: UserDTO? = null

    var supervisor: UserDTO? = null

    var notes: Set<NoteDTO>? = null

    var files: Set<FileDTO>? = null


}
