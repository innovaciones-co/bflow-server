package co.innovaciones.bflow_server.service

data class EmailDetails(
    val recipient: String,
    val subject: String,
    val templateId: Long,
    val content: String,
    val params: Map<String,Any>
)
