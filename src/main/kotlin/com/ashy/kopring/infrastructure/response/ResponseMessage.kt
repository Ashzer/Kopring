package com.ashy.kopring.infrastructure.response

import com.ashy.kopring.infrastructure.constants.ErrorConst
import com.ashy.kopring.infrastructure.extensions.empty
import org.springframework.http.HttpStatus

data class ResponseMessage<T>(
    var status: HttpStatus,
    val message: String = String().empty(),
    val fieldErrors: Map<String, String> = emptyMap(),
    val keyError: String = String().empty(),
    val errorCode: String?
) {
    var data: T? = null

    val succeeded: Boolean
        get() = status.value() in 200 until 300

    companion object {
        fun <T> of(
            status: HttpStatus, message: String, fieldErrors: Map<String, String>, data: T, keyError: String
        ): ResponseMessage<T> {
            val responseMessage = ResponseMessage<T>(status, message, fieldErrors, keyError, "")
            responseMessage.data = data
            return responseMessage
        }

        fun <T> of(status: HttpStatus): ResponseMessage<T> {
            val responseMessage = ResponseMessage<T>(status, "", emptyMap(), "", "")
            return responseMessage
        }

        fun <T> of(status: HttpStatus, data: T): ResponseMessage<T> {
            val responseMessage = of(status, "", emptyMap(), data, "")
            responseMessage.status = status
            return responseMessage
        }

        fun <T> ofFailure(status: HttpStatus, errorConst: ErrorConst, fieldErrors: Map<String, String>) =
            ResponseMessage<T>(status, errorConst.message, fieldErrors, errorConst.name, errorConst.code)
    }/*
    fun <T> ofBadRequest(message: String, fieldErrors: Map<String, String>, keyError: String) =
        ResponseMessage<T>(message, fieldErrors, keyError, null)

    fun <T> ofBadRequest(message: String, fieldErrors: Map<String, String>, keyError: String, errorCode: String) =
        ResponseMessage<T>(message, fieldErrors, keyError, errorCode)
        */
}