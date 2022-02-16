package com.example.EmployeeMicroservice.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.function.Consumer

@RestControllerAdvice
class CustomExceptionHandler {

    private val LOGGER = LoggerFactory.getLogger(CustomExceptionHandler::class.java)

    @ExceptionHandler(EmployeeIDNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleUserNotFoundException(exception: EmployeeIDNotFoundException): ResponseError? {
        LOGGER.info(exception.message, exception)
        return ResponseError(HttpStatus.NOT_FOUND, exception.message)
    }

    @ExceptionHandler(ConnectionFailureException::class)
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    fun handleConnectionNotFoundException(exception: ConnectionFailureException): ResponseError? {
        LOGGER.info(exception.message, exception)
        return ResponseError(HttpStatus.REQUEST_TIMEOUT, exception.message)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValid(exception: MethodArgumentNotValidException): FieldErrorResponse? {
        LOGGER.error("Bean validation failed with errors", exception)
        val fieldErrors = getFieldErrors(exception)
        return FieldErrorResponse(HttpStatus.BAD_REQUEST, "Invalid input", fieldErrors)
    }

    private fun getFieldErrors(exception: MethodArgumentNotValidException): List<FieldErrorResponse.FieldError> {
        val fieldErrors: MutableList<FieldErrorResponse.FieldError> = ArrayList()
        exception.bindingResult.fieldErrors.forEach(
            Consumer { objectError: FieldError ->
                val field = objectError.field
                val error = objectError.defaultMessage
                fieldErrors.add(FieldErrorResponse.FieldError(field, error))
            }
        )
        return fieldErrors
    }
}
