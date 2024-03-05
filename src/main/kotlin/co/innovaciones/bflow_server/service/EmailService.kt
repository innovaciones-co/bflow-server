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
    private val emailApi: TransactionalEmailsApi
) {

    fun sendEmail(to: String): String {

        var emailConfirmation: String

        val sendSmtpEmail = SendSmtpEmail()
        val to = SendSmtpEmailTo()
        val sender = SendSmtpEmailSender()
        to.email = "diegofelipere@gmail.com"
        sender.email = "test@correo.com"
        sendSmtpEmail.to = listOf(to)
        sendSmtpEmail.sender = sender
        sendSmtpEmail.subject = "Test Subject from Kotlin"
        sendSmtpEmail.htmlContent = "<p>This is the HTML content of the email</p>"

        try {
            val response = emailApi.sendTransacEmail(sendSmtpEmail)
            emailConfirmation = ("email sent successfully. Response: $response")
        } catch (e: ApiException) {
            emailConfirmation = ("exception when calling TransactionalEmailsApi#sendTransacEmail: $e")
        }

        return emailConfirmation

    }
}