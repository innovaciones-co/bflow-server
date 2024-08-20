package co.innovaciones.bflow_server.service

class NotificationFactory {
    fun createNotificationBuilder(type: String, emailService: EmailService): NotificationBuilder {
        return when (type) {
            "email" -> EmailNotificationBuilder(emailService)
            else -> throw IllegalArgumentException("Invalid notification type: $type")
        }
    }
}