package com.example.EmployeeMicroservice.exception

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatus

class FieldErrorResponse(status: HttpStatus?, message: String?, @field:JsonProperty private val errors: List<FieldError>) : ResponseError(status!!, message) {

    class FieldError(
        @field:JsonProperty
        private val field: String? = null,
        @field:JsonProperty
        private val error: String? = null
    )
}
