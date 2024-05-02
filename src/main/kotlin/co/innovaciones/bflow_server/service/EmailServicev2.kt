package co.innovaciones.bflow_server.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class EmailServiceV2(strategies: List<EmailStrategy>)
{
    private val strategiesMap = strategies.associateBy { it.getType() }

    fun sendEmail(type: String,data: Map<String,Any>) {
        val emailDetails = createEmailDetails(type, data)
        strategiesMap[type]?.sendEmail(emailDetails)
            ?: throw IllegalArgumentException("No Email strategy found for type: $type")

    }

    private fun createEmailDetails(type: String,data: Map<String, Any>):EmailDetails {
            return EmailDetails(
                recipient = data["recipient"] as String,
                subject = data["subject"] as String,
                content = data["content"] as String,
                params = data,
                templateId = data["templateId"] as Long
            )
    }


}

