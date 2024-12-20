package co.innovaciones.bflow_server.service

import brevoApi.TransactionalEmailsApi
import brevoModel.SendSmtpEmail
import brevoModel.SendSmtpEmailAttachment
import brevoModel.SendSmtpEmailTo
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class
EmailService(
    private val emailApi: TransactionalEmailsApi,
    private val sendSmtpEmail: SendSmtpEmail
) {

    fun sendEmail(recipients: List<String>, subject: String, content: String, template: Long?, params: Map<String, Any>, attachments: List<SendSmtpEmailAttachment>?) {
        sendSmtpEmail.to = recipients.map { recipient -> SendSmtpEmailTo().apply { email = recipient } }
        sendSmtpEmail.subject = subject
        sendSmtpEmail.params = params
        if (template != null) {
            sendSmtpEmail.templateId = template
        } else {
            sendSmtpEmail.htmlContent = content
        }
        sendSmtpEmail.attachment = attachments
        emailApi.sendTransacEmail(sendSmtpEmail)
    }
}
