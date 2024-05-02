package co.innovaciones.bflow_server.service

interface EmailStrategy {
    fun sendEmail(emailDetails: EmailDetails)
}