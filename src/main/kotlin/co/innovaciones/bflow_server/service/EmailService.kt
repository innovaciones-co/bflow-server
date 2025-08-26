package co.innovaciones.bflow_server.service

import brevo.ApiException
import brevoApi.TransactionalEmailsApi
import brevoModel.SendSmtpEmail
import brevoModel.SendSmtpEmailAttachment
import brevoModel.SendSmtpEmailTo
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
@Transactional
class
EmailService(
    private val emailApi: TransactionalEmailsApi,
    private val sendSmtpEmail: SendSmtpEmail
) {
    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun sendEmail(recipients: List<String>, subject: String, content: String, template: Long?, params: Map<String, Any>, attachments: List<SendSmtpEmailAttachment>?) {
        sendSmtpEmail.to = recipients.map { recipient -> SendSmtpEmailTo().apply { email = recipient } }
        sendSmtpEmail.subject = subject
        sendSmtpEmail.params = params
        if (template != null) {
            sendSmtpEmail.templateId = template
        } else {
            sendSmtpEmail.htmlContent = content
        }
        if (!attachments.isNullOrEmpty()) {
            sendSmtpEmail.attachment = attachments
        }
        try {
            emailApi.sendTransacEmail(sendSmtpEmail)
        } catch (e: ApiException) {
            log.error("Email service replied with status " + e.code.toString() + ": " + e.responseBody)
        }
    }
}
