package co.innovaciones.bflow_server.service

import sendinblue.ApiException
import sibApi.TransactionalEmailsApi
import sibModel.SendSmtpEmail
import sibModel.SendSmtpEmailSender
import sibModel.SendSmtpEmailTo

class EmailByBrevo: EmailStrategy {
    override fun sendEmail(emailDetails: EmailDetails) {
        send(emailDetails.recipient, emailDetails.subject, emailDetails.content)
    }

    private fun send(recipient: String,subject:String,content:String): String {
        var emailConfirmation: String
        val sendSmtpEmail = SendSmtpEmail()
        val emailApi = TransactionalEmailsApi()
        val to = SendSmtpEmailTo()
        to.email = recipient
        sendSmtpEmail.subject = subject
        sendSmtpEmail.htmlContent = content
        sendSmtpEmail.to = listOf(to)
        sendSmtpEmail.params = mapOf(
            "TASK" to "10003",
            "TASKDATA" to "Dummy"
        )
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