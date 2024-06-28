package co.innovaciones.bflow_server.model

class TaskWriteDTO: TaskDTO() {
    var supplier: Long? = null

    var attachments: List<Long>? = null

    override fun toString(): String {
        return this.id.toString()
    }
}