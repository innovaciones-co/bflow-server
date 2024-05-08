package co.innovaciones.bflow_server.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import sendinblue.ApiException
import sibApi.TransactionalEmailsApi
import sibModel.SendSmtpEmail
import sibModel.SendSmtpEmailTo


class EmailNotification(
    val content: String,
    val subject: String,
    val recipients:List<String>,
    private val emailService: EmailService,
    val params:Map<String,Any>): NotificationStrategy {
    override fun send() {
        println("Sent $content by email to $recipients with subject: $subject")
        //sendEmail(recipients, subject, content)
        val confirmation = emailService.sendEmail(recipients,subject,content,params)
        println("EmailService: $confirmation")
    }
}