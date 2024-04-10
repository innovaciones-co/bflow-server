package co.innovaciones.bflow_server.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import sibApi.TransactionalEmailsApi
import sibModel.SendSmtpEmail

@Configuration
class BrevoConfig {
    @Value("\${sendinblue.api-key}")
    private val apiKey: String = ""

    @Bean
    fun mailClient(): TransactionalEmailsApi{
        val transactionalEmailsApi = TransactionalEmailsApi()
        transactionalEmailsApi.apiClient.setApiKey(apiKey)
        return transactionalEmailsApi
    }


    @Bean
    fun mailSender(): SendSmtpEmail{
        val sendSmtpEmail = SendSmtpEmail()
        //sendSmtpEmail.sender.email= "test@correo.com"
        return sendSmtpEmail
    }

}
