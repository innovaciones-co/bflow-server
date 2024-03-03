package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.model.TaskDTO
import jakarta.transaction.Transactional
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Service
import sendinblue.ApiClient
import sendinblue.ApiException
import sibApi.TransactionalEmailsApi
import sibModel.SendSmtpEmail
import sibModel.SendSmtpEmailSender
import sibModel.SendSmtpEmailTo

@Service
@Transactional
class EmailService(
) {
    fun correo(): String {

        val apiKey = "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"

        var emailConfirmation: String

        val emailApi = TransactionalEmailsApi()
        emailApi.apiClient.setApiKey(apiKey)
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
            val reponse = emailApi.sendTransacEmail(sendSmtpEmail)
            emailConfirmation = ("email sent successfully. Response: $reponse")
        } catch (e: ApiException) {
            emailConfirmation = ("exception when calling TransactionalEmailsApi#sendTransacEmail: $e")
        }

        return emailConfirmation

    }
}