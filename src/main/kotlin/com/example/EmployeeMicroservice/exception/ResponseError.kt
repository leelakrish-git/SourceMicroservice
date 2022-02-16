package com.example.EmployeeMicroservice.exception

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatus

open class ResponseError {
    @JsonProperty
    var status: Status? = null
        private set
    @JsonProperty
    var message: String? = null
        private set

    constructor() {}
    constructor(status: HttpStatus, message: String?) {
        this.status = Status(status)
        this.message = message
    }

    class Status(@get:JsonIgnore val httpStatus: HttpStatus) {
        @get:JsonProperty
        val code: Int
            get() = httpStatus.value()

        @get:JsonProperty
        val reason: String
            get() = httpStatus.reasonPhrase
    }
}
