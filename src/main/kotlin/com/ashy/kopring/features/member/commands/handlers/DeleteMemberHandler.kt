package com.ashy.kopring.features.member.commands.handlers

import an.awesome.pipelinr.Command.Handler
import com.ashy.kopring.features.member.commands.DeleteMember
import com.ashy.kopring.infrastructure.constants.ErrorConst
import com.ashy.kopring.infrastructure.failures.DatabaseFailure
import com.ashy.kopring.infrastructure.repositories.MemberRepository
import com.ashy.kopring.infrastructure.response.ResponseMessage
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DeleteMemberHandler(private val memberRepository: MemberRepository) :
    Handler<DeleteMember, ResponseMessage<String>> {
        
    override fun handle(command: DeleteMember): ResponseMessage<String> {
        return memberRepository.deleteMemberById(command.id).fold(onSuccess = {
            if (it == 0) {
                ResponseMessage.ofBadRequest(
                    HttpStatus.NOT_FOUND, ErrorConst.MEMBER_NOT_FOUND, mapOf("id" to "${command.id}")
                )
            } else {
                ResponseMessage.of(HttpStatus.ACCEPTED, "Member deleted with id: ${command.id}")
            }
        }, onFailure = { failure ->
            val status = when (failure) {
                is DatabaseFailure.ConstraintViolationFailure -> HttpStatus.CONFLICT
                is DatabaseFailure.SQLSyntaxFailure -> HttpStatus.BAD_REQUEST
                else -> HttpStatus.INTERNAL_SERVER_ERROR
            }
            ResponseMessage.ofBadRequest(
                status,
                ErrorConst.MEMBER_FAILED_TO_DELETE,
                mapOf("id" to "${command.id}", "message" to (failure.message ?: "None"))
            )
        })
    }
}