package com.ashy.kopring.features.member.commands.handlers

import an.awesome.pipelinr.Command.Handler
import com.ashy.kopring.features.member.commands.DeleteMember
import com.ashy.kopring.infrastructure.constants.ErrorConst
import com.ashy.kopring.infrastructure.repositories.MemberRepository
import com.ashy.kopring.infrastructure.response.ResponseMessage
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class DeleteMemberHandler(private val memberRepository: MemberRepository) :
    Handler<DeleteMember, ResponseMessage<String>> {

    override fun handle(command: DeleteMember): ResponseMessage<String> {

        memberRepository.deleteMemberById(command.id).fold(onSuccess = {
            if (it == 0) {
                return ResponseMessage.ofBadRequest(
                    HttpStatus.NOT_FOUND, ErrorConst.MEMBER_NOT_FOUND, mapOf("id" to "${command.id}")
                )
            } else {
                return ResponseMessage.of(HttpStatus.ACCEPTED, "Member deleted with id: ${command.id}")
            }
        }, onFailure = {
            return ResponseMessage.ofBadRequest(
                HttpStatus.INTERNAL_SERVER_ERROR, ErrorConst.MEMBER_FAILED_TO_DELETE, mapOf("id" to "${command.id}")
            )
        })
    }
}