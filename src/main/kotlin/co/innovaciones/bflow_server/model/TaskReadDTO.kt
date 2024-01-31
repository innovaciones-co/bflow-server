package co.innovaciones.bflow_server.model

import java.time.OffsetDateTime

class TaskReadDTO: TaskDTO() {
    var bookingDate: OffsetDateTime? = null

    var supplier: ContactDTO? = null
}