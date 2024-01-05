package co.innovaciones.bflow_server.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate


class JobDTO {

    var id: Long? = null

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

    @Size(max = 255)
    var contract: String? = null

    var description: String? = null

    @NotNull
    @Size(max = 255)
    var buildingType: String? = null

    @NotNull
    var client: Long? = null

    @NotNull
    var user: Long? = null

}
