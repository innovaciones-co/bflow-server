package co.innovaciones.bflow_server.model

import co.innovaciones.bflow_server.model.validators.JobMatch

@JobMatch
class TaskWriteDTO: TaskDTO() {
    var supplier: Long? = null

    var attachments: List<Long>? = null

    var purchaseOrder: Long? = null

    override fun toString(): String {
        return this.id.toString()
    }
}