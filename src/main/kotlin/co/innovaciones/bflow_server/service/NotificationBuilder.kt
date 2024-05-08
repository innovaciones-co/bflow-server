package co.innovaciones.bflow_server.service

interface NotificationBuilder {
    fun withContent(content: String) :NotificationBuilder
    fun withSubject(subject: String): NotificationBuilder
    fun withRecipients(vararg recipients:String) : NotificationBuilder
    fun build(): NotificationStrategy
}