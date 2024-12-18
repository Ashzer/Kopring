package com.ashy.kopring.infrastructure.response

import com.ashy.kopring.infrastructure.constants.ErrorConst
import com.ashy.kopring.infrastructure.extensions.empty
import org.springframework.http.HttpStatus
import java.net.URI

data class ResponseMessage<T>(
    val message: String = String().empty(),
    val fieldErrors: Map<String, String> = emptyMap(),
    val keyError: String = String().empty(),
    val errorCode: String?
) {

    var status: HttpStatus = HttpStatus.OK
    var data: T? = null
    var location: URI? = null

    val succeeded: Boolean
        get() = status.value() in 200 until 300

    companion object {
        fun <T> of(
            status: HttpStatus, message: String, fieldErrors: Map<String, String>, data: T, keyError: String
        ): ResponseMessage<T> {
            val responseMessage = ResponseMessage<T>(message, fieldErrors, keyError, "")
            responseMessage.status = status
            responseMessage.data = data
            return responseMessage
        }

        fun <T> of(status: HttpStatus): ResponseMessage<T> {
            val responseMessage = ResponseMessage<T>("", emptyMap(), "", "")
            responseMessage.status = status
            return responseMessage
        }

        fun <T> of(status: HttpStatus, data: T): ResponseMessage<T> {
            val responseMessage = of(status, "", emptyMap(), data, "")
            responseMessage.status = status
            return responseMessage
        }

        fun <T> ofBadRequest(status: HttpStatus, errorConst: ErrorConst, fieldErrors: Map<String, String>) =
            ResponseMessage<T>(errorConst.message, fieldErrors, errorConst.name, errorConst.code)
    }
    /*fun <T> ofBadRequest(message: String, fieldErrors: Map<String, String>, keyError: String) =
        ResponseMessage<T>(message, fieldErrors, keyError, null)

    fun <T> ofBadRequest(message: String, fieldErrors: Map<String, String>, keyError: String, errorCode: String) =
        ResponseMessage<T>(message, fieldErrors, keyError, errorCode)*/
}