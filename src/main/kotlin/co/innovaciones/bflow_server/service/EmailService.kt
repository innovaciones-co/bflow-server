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

    fun sendEmail(toSend: String, taskData: String): String {

        var emailConfirmation: String

        val to = SendSmtpEmailTo()
        val sender = SendSmtpEmailSender()
        to.email = toSend
        sendSmtpEmail.to = listOf(to)
        sender.email = "test@correo.com"
        sendSmtpEmail.sender = sender
        sendSmtpEmail.subject = "Notificacion for task: $taskData"
        sendSmtpEmail.htmlContent = "<p>This is the HTML content of the email /n $taskData</p>"
        //val params = sendSmtpEmail.params("{\"TASK\":\"10003\",\"SUPPLIER\":\"$taskData\"}")
        sendSmtpEmail.params = mapOf(
            "TASK" to "10003",
            "TASKDATA" to "$taskData"
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
