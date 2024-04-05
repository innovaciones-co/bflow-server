package co.innovaciones.bflow_server.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate


class JobDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    @JobJobNumberUnique
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

   var stage: TaskStage = TaskStage.SLAB_DOWN

    var progress: Double = 0.0

}
