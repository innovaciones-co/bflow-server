package co.innovaciones.bflow_server.config

import brevoApi.TransactionalEmailsApi
import brevoModel.SendSmtpEmail
import brevoModel.SendSmtpEmailReplyTo
import brevoModel.SendSmtpEmailSender
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BrevoConfig {
    @Value("\${sendinblue.api-key}")
    private val apiKey: String = ""

    @Value("\${sendinblue.from-email}")
    private val senderEmail: String = ""

    @Value("\${sendinblue.from-name}")
    private val senderName: String = ""

    @Bean
    fun mailClient(): TransactionalEmailsApi {
        val transactionalEmailsApi = TransactionalEmailsApi()
        transactionalEmailsApi.apiClient.setApiKey(apiKey)
        return transactionalEmailsApi
    }


    @Bean
    fun mailSender(): SendSmtpEmail {
        val sendSmtpEmail = SendSmtpEmail()
        val sender = SendSmtpEmailSender().apply {
            email = senderEmail
            name = senderName
        }
        val replyTo =  SendSmtpEmailReplyTo().apply {
            email = senderEmail
            name = senderName
        }
        sendSmtpEmail.sender = sender
        sendSmtpEmail.replyTo = replyTo

        return sendSmtpEmail
    }

}
