package co.innovaciones.bflow_server.model

import co.innovaciones.bflow_server.model.validators.UniqueJobNumber
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate


class JobDTO {

    var id: Long? = null

    @UniqueJobNumber
    @NotNull
    @Size(max = 255)
    var jobNumber: String? = null

    @Size(max = 255)
    var name: String? = null

    var plannedStartDate: LocalDate? = null

    var plannedEndDate: LocalDate? = null

    @NotNull
    @Size(max = 255)
    var address: String? = null

    var description: String? = null

    @NotNull
    var buildingType: BuildingType? = null

    @NotNull
    var client: Long? = null

    @NotNull
    var user: Long? = null

    var notes: Set<NoteDTO>? = null

    var files: Set<FileDTO>? = null

}
