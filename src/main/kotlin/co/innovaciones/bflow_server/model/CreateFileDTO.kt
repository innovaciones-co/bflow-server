package co.innovaciones.bflow_server.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.web.multipart.MultipartFile

class CreateFileDTO {

    @NotNull
    @Size(max = 255)
    var name: String? = null

    @NotNull
    var category: FileCategory? = null

    var tag: FileTag? = null

    var job: Long? = null

    @NotNull
    var file: MultipartFile? = null

    @NotNull
    var entity: FileEntity = FileEntity.JOB

}

