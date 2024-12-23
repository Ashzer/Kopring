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

    @Transactional
    override fun handle(command: DeleteMember): ResponseMessage<String> {
        return memberRepository.deleteMemberById(command.id).fold(onSuccess = {
            ResponseMessage.of(HttpStatus.ACCEPTED, "Member deleted with id: ${command.id}")

        }, onFailure = { failure ->
            when (failure) {
                is DatabaseFailure.ConstraintViolationFailure -> HttpStatus.CONFLICT to ErrorConst.MEMBER_CANNOT_BE_DELETED
                is DatabaseFailure.SQLSyntaxFailure -> HttpStatus.BAD_REQUEST to ErrorConst.MEMBER_FAILED_TO_DELETE
                is DatabaseFailure.DataNotFoundFailure -> HttpStatus.NOT_FOUND to ErrorConst.MEMBER_NOT_FOUND
                else -> HttpStatus.INTERNAL_SERVER_ERROR to ErrorConst.MEMBER_FAILED_TO_DELETE
            }.let { (status, errorConst) ->
                ResponseMessage.ofBadRequest(
                    status, errorConst, mapOf("id" to "${command.id}")
                )
            }
        })
    }
}