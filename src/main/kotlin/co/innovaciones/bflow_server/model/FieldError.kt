package co.innovaciones.bflow_server.model


data class FieldError(
    var `field`: String? = null,
    var errorCode: String? = null,
    var message: String? = null
)
