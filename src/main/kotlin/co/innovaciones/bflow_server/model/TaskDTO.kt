package co.innovaciones.bflow_server.model

import co.innovaciones.bflow_server.model.validators.EndDateGreaterThanStartDate
import co.innovaciones.bflow_server.model.validators.WithinParentDateRange
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate
import java.time.OffsetDateTime

@EndDateGreaterThanStartDate
@WithinParentDateRange
open class TaskDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    var name: String? = null

    @NotNull
    var startDate: LocalDate? = null

    @NotNull
    var endDate: LocalDate? = null

    var bookingDate: OffsetDateTime? = null

    @NotNull
    var progress: Double? = null

    @NotNull
    var status: TaskStatus? = null

    @NotNull
    var stage: TaskStage? = null

    var description: String? = null

    @Min(0)
    @Max(10000)
    var order: Int? = null

    var parentTask: Long? = null

    @NotNull
    var job: Long? = null

}
