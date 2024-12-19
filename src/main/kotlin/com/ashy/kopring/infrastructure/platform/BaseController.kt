package com.ashy.kopring.infrastructure.platform

import an.awesome.pipelinr.Command
import an.awesome.pipelinr.Pipeline
import com.ashy.kopring.infrastructure.constants.ErrorConst
import com.ashy.kopring.infrastructure.extensions.logger
import com.ashy.kopring.infrastructure.response.ResponseMessage
import com.ashy.kopring.infrastructure.services.AuthenticationManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
abstract class BaseController(val pipeline: Pipeline) {
    abstract fun preProcessRequest(command: Command<*>): HttpStatus

    protected fun <T> handleWithResponseMessage(command: Command<ResponseMessage<T>>): ResponseEntity<*> {
        try {
            val preProcessStatus = preProcessRequest(command)
            if (preProcessStatus == HttpStatus.ACCEPTED) {
                val message = command.execute(pipeline)
                if (message.succeeded) {
                    return ResponseEntity.status(message.status).body(message.data)
                }
                return ResponseEntity.status(message.status).body(
                    ResponseMessage<T>(
                        status = message.status,
                        message = message.message,
                        fieldErrors = message.fieldErrors,
                        keyError = message.keyError,
                        errorCode = message.errorCode
                    )
                )
            }

            if (preProcessStatus == HttpStatus.NOT_ACCEPTABLE) {
                return ResponseEntity.badRequest().body<ResponseMessage<T>>(
                    ResponseMessage.ofBadRequest(
                        status = HttpStatus.NOT_ACCEPTABLE,
                        errorConst = ErrorConst.INVALID_DATA,
                        fieldErrors = emptyMap()
                    )
                )
            }

            return ResponseEntity.status(preProcessStatus).build<Any>()
        } catch (e: Exception) {
            logger().error("Error: ${e.message}")
            return ResponseEntity.internalServerError().build<Any>()
        }
    }
}