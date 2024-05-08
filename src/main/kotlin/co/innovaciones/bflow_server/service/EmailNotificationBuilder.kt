package co.innovaciones.bflow_server.service

class EmailNotificationBuilder(private val emailService: EmailService):NotificationBuilder {
    private var content:String=""
    private var subject:String=""
    private val recipients = mutableListOf<String>()

    override fun withContent(content: String): NotificationBuilder {
        this.content=content
        return this
    }
    override fun withSubject(subject: String): NotificationBuilder {
        this.subject=subject
        return this
    }

    override fun withRecipients(vararg recipients: String): NotificationBuilder {
        this.recipients.addAll(recipients)
        return this
    }

    override fun build(): NotificationStrategy {
        return EmailNotification(content,subject,recipients,emailService)
    }
}
