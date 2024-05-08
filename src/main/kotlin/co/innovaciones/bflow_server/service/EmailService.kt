package co.innovaciones.bflow_server.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import sendinblue.ApiException
import sibApi.TransactionalEmailsApi
import sibModel.SendSmtpEmail
import sibModel.SendSmtpEmailSender
import sibModel.SendSmtpEmailTo

@Service
@Transactional
class EmailService(
    private val emailApi: TransactionalEmailsApi,
    private val sendSmtpEmail: SendSmtpEmail
) {

    fun sendEmail(recipients: List<String>, subject:String, content:String, params:Map<String,Any>): String  {

        var emailConfirmation: String

        val sender = SendSmtpEmailSender()
        sender.email = "diegofelipere@hotmail.com"
        sender.name = "Diego"
        val replyTo = SendSmtpEmailTo()
        replyTo.email = sender.email
        replyTo.name = sender.name

        val to = SendSmtpEmailTo()
        for (recipient in recipients) {
            to.email = recipient
        }
        sendSmtpEmail.to = listOf(to)
        sendSmtpEmail.subject = subject
        sendSmtpEmail.htmlContent = content
        sendSmtpEmail.params = params
        sendSmtpEmail.templateId = 1

        try {
            val response = emailApi.sendTransacEmail(sendSmtpEmail)
            emailConfirmation = ("email sent successfully. Response: $response")
        } catch (e: ApiException) {
            emailConfirmation = ("exception when calling TransactionalEmailsApi#sendTransacEmail: $e")
        }

        return emailConfirmation

    }
}
