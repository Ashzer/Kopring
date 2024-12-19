package com.ashy.kopring.infrastructure.platform

import an.awesome.pipelinr.Command
import an.awesome.pipelinr.Pipeline
import com.ashy.kopring.infrastructure.repositories.MemberRepository
import com.ashy.kopring.infrastructure.services.AuthenticationManager
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class BaseUserController(
    pipeline: Pipeline, val authenticationManager: AuthenticationManager, val memberRepository: MemberRepository
) : BaseController(pipeline) {

    override fun preProcessRequest(command: Command<*>): HttpStatus {
        return if (command is BaseIdentityCommand<*>) {
            //TODO implement authentication
            HttpStatus.ACCEPTED
        } else {
            HttpStatus.NOT_ACCEPTABLE
        }
    }
}