package co.innovaciones.bflow_server.model

import jakarta.validation.constraints.NotNull


class NoteDTO {

    var id: Long? = null

    @NotNull
    var body: String? = null

    var job: Long? = null

}
