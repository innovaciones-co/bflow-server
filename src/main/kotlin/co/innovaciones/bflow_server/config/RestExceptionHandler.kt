package co.innovaciones.bflow_server.config

import co.innovaciones.bflow_server.model.ErrorResponse
import co.innovaciones.bflow_server.model.FieldError
import co.innovaciones.bflow_server.util.NotFoundException
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice
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

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValid(exception: MethodArgumentNotValidException):
            ResponseEntity<ErrorResponse> {
        val bindingResult: BindingResult = exception.bindingResult
        val fieldErrors: List<FieldError> = bindingResult.fieldErrors
                .stream()
                .map { error ->
                    val fieldError = FieldError()
                    fieldError.errorCode = error.code
                    fieldError.field = error.field
                    fieldError.message = error.defaultMessage
                    fieldError
                }
                .toList()
        val errorResponse = ErrorResponse()
        errorResponse.httpStatus = HttpStatus.BAD_REQUEST.value()
        errorResponse.exception = exception::class.simpleName
        errorResponse.fieldErrors = fieldErrors
        errorResponse.message = "Invalid arguments"
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
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
        responseCode = "4xx/5xx",
        description = "Error"
    )
    fun handleThrowable(exception: Throwable): ResponseEntity<ErrorResponse> {
        exception.printStackTrace()
        val errorResponse = ErrorResponse()
        errorResponse.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value()
        errorResponse.exception = exception::class.simpleName
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}
