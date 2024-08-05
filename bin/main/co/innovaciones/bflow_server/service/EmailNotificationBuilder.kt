package co.innovaciones.bflow_server.service

class EmailNotificationBuilder(private val emailService: EmailService) : NotificationBuilder {
    private var content: String = ""
    private var subject: String = ""
    private var recipients = mutableListOf<String>()
    private var params: Map<String, Any> = emptyMap()
    private var template: Long? = null

    override fun withContent(content: String): NotificationBuilder {
        this.content = content
        return this
    }

    override fun withSubject(subject: String): NotificationBuilder {
        this.subject = subject
        return this
    }

    override fun withRecipients(vararg recipients: String): NotificationBuilder {
        this.recipients.addAll(recipients)
        return this
    }

    fun withParams(params: Map<String, Any>): NotificationBuilder {
        this.params = params
        return this
    }

    fun withTemplate(template: Long): EmailNotificationBuilder {
        this.template = template
        return this
    }

    override fun build(): NotificationStrategy {
        return EmailNotification(content, subject, recipients, emailService, params, template)
    }
}
