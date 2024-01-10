package co.innovaciones.bflow_server.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


class FileDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    var uri: String? = null

    @NotNull
    @Size(max = 255)
    var name: String? = null

    @Size(max = 255)
    var type: String? = null

    @NotNull
    var category: FileCategory? = null

    var tag: FileTag? = null

    var job: Long? = null

}
