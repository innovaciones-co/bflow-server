package co.innovaciones.bflow_server.service

import brevoModel.SendSmtpEmailAttachment
import org.slf4j.LoggerFactory


class EmailNotification(
    private val content: String,
    private val subject: String,
    private val recipients: List<String>,
    private val emailService: EmailService,
    private val params: Map<String, Any>,
    private val template: Long?,
    private val attachments: List<SendSmtpEmailAttachment>?
) : NotificationStrategy {

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun send() {
        log.info("Sending a notification by email to $recipients with subject: $subject.")
        emailService.sendEmail(recipients, subject, content, template, params, attachments)
    }
}