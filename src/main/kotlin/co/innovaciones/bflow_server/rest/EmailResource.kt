package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.TaskCreateUpdateDTO
import co.innovaciones.bflow_server.service.EmailService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    value = ["/api/email"], produces = [MediaType.APPLICATION_JSON_VALUE]
)
class EmailResource (
    private val emailService: EmailService
) {
    @PostMapping
    @ApiResponse(responseCode = "201")
    fun sendEmailList(): ResponseEntity<String> {
        val emailSended = emailService.correo()
        return ResponseEntity(emailSended, HttpStatus.CREATED)
    }
}