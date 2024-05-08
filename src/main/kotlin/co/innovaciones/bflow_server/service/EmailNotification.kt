package co.innovaciones.bflow_server.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import sendinblue.ApiException
import sibApi.TransactionalEmailsApi
import sibModel.SendSmtpEmail
import sibModel.SendSmtpEmailTo


class EmailNotification(val content: String, val subject: String, val recipients:List<String>, private val emailService: EmailService): NotificationStrategy {
    override fun send() {
        println("Sent $content by email to $recipients with subject: $subject")
        sendEmail(recipients, subject, content)
        val confirmation = emailService.sendEmail(recipients.joinToString(),"10000")
        println("EmailService: $confirmation")
    }

    private fun sendEmail(recipients: List<String>, subject:String, content:String): String {
        var emailConfirmation: String
        val sendSmtpEmail = SendSmtpEmail()
        val emailApi = TransactionalEmailsApi()
        val to = SendSmtpEmailTo()
        for (recipient in recipients) {
            to.email = recipient
        }
        sendSmtpEmail.subject = subject
        sendSmtpEmail.htmlContent = content
        sendSmtpEmail.to = listOf(to)
        //sendSmtpEmail.params = mapOf(
        //    "TASK" to "10003",
        //    "TASKDATA" to "Dummy"
        //)
        //sendSmtpEmail.templateId = 1

        try {
            val response = emailApi.sendTransacEmail(sendSmtpEmail)
            emailConfirmation = ("email sent successfully. Response: $response")
        } catch (e: ApiException) {
            emailConfirmation = ("exception when calling TransactionalEmailsApi#sendTransacEmail: $e")
        }

        return emailConfirmation

    }
}