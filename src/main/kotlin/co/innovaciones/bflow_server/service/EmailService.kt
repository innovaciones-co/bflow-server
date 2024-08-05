package co.innovaciones.bflow_server.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import sibApi.TransactionalEmailsApi
import sibModel.SendSmtpEmail
import sibModel.SendSmtpEmailTo

@Service
@Transactional
class
EmailService(
    private val emailApi: TransactionalEmailsApi,
    private val sendSmtpEmail: SendSmtpEmail
) {

    fun sendEmail(recipients: List<String>, subject: String, content: String, template: Long?, params: Map<String, Any>) {
        sendSmtpEmail.to = recipients.map { recipient -> SendSmtpEmailTo().apply { email = recipient } }
        sendSmtpEmail.subject = subject
        sendSmtpEmail.params = params
        if (template != null) {
            sendSmtpEmail.templateId = template
        } else {
            sendSmtpEmail.htmlContent = content
        }
        emailApi.sendTransacEmail(sendSmtpEmail)
    }
}
