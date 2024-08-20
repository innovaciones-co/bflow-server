package co.innovaciones.bflow_server.config

import co.innovaciones.bflow_server.model.ErrorResponse
import co.innovaciones.bflow_server.model.FieldError
import co.innovaciones.bflow_server.util.NotFoundException
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.HandlerMethodValidationException
import org.springframework.web.server.ResponseStatusException


@RestControllerAdvice(annotations = [RestController::class])
class RestExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(exception: NotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse()
        errorResponse.httpStatus = HttpStatus.NOT_FOUND.value()
        errorResponse.exception = exception::class.simpleName
        errorResponse.message = exception.message
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(HandlerMethodValidationException::class)
    fun handleMethodValidation(exception: HandlerMethodValidationException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse()
        errorResponse.httpStatus = HttpStatus.BAD_REQUEST.value()
        errorResponse.exception = exception::class.simpleName
        errorResponse.message = exception.message
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValid(exception: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val bindingResult: BindingResult = exception.bindingResult
        var fieldErrors: List<FieldError> = bindingResult.fieldErrors.stream().map { error ->
            val fieldError = FieldError()
            fieldError.errorCode = error.code
            fieldError.field = error.field
            fieldError.message = error.defaultMessage
            fieldError
        }.toList()
        fieldErrors = fieldErrors.plus(bindingResult.allErrors.stream().map { e ->
            val fieldError = FieldError()
            fieldError.message = e.defaultMessage
            fieldError.errorCode = e.code
            fieldError
        }.toList())

        val errorResponse = ErrorResponse()
        errorResponse.httpStatus = HttpStatus.BAD_REQUEST.value()
        errorResponse.exception = exception::class.simpleName
        errorResponse.fieldErrors = fieldErrors
        errorResponse.message = "Invalid arguments"
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun handleConstraintViolationExceptions(
        exception: ConstraintViolationException
    ): ResponseEntity<ErrorResponse> {
        val errors = exception.constraintViolations.map { violation -> violation.message }
        val fieldErrors: List<FieldError> = errors.stream().map { error ->
            val fieldError = FieldError()
            fieldError.message = error
            fieldError
        }.toList()
        val errorResponse = ErrorResponse()
        errorResponse.httpStatus = HttpStatus.BAD_REQUEST.value()
        errorResponse.exception = exception::class.simpleName
        errorResponse.fieldErrors = fieldErrors
        errorResponse.message = "Invalid arguments"

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [DataIntegrityViolationException::class])
    fun handleConstraintViolationExceptions(
        exception: DataIntegrityViolationException
    ): ResponseEntity<ErrorResponse> {

        val errorResponse = ErrorResponse()
        errorResponse.httpStatus = HttpStatus.CONFLICT.value()
        errorResponse.exception = exception::class.simpleName
        errorResponse.fieldErrors = listOf(FieldError(message = extractDetailSection(exception.message)))
        errorResponse.message = "Data integrity violation."

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    private fun extractDetailSection(input: String?): String? {
        val regex = Regex("Detail: (.+?)]")
        val matchResult = if(input != null) regex.find(input) else null
        return matchResult?.groupValues?.get(1) ?: input
    }

    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatus(exception: ResponseStatusException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse()
        errorResponse.httpStatus = exception.statusCode.value()
        errorResponse.exception = exception::class.simpleName
        errorResponse.message = exception.message
        return ResponseEntity(errorResponse, exception.statusCode)
    }

    @ExceptionHandler(Throwable::class)
    @ApiResponse(
        responseCode = "4xx/5xx", description = "Error"
    )
    fun handleThrowable(exception: Throwable): ResponseEntity<ErrorResponse> {
        exception.printStackTrace()
        val errorResponse = ErrorResponse()
        errorResponse.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value()
        errorResponse.exception = exception::class.simpleName
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}
