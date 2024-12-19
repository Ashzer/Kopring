package com.ashy.kopring.features.member.commands.handlers

import an.awesome.pipelinr.Command.Handler
import com.ashy.kopring.features.member.commands.DeleteMember
import com.ashy.kopring.infrastructure.repositories.MemberRepository
import com.ashy.kopring.infrastructure.response.ResponseMessage
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class DeleteMemberHandler(private val memberRepository: MemberRepository) :
    Handler<DeleteMember, ResponseMessage<String>> {
    override fun handle(command: DeleteMember): ResponseMessage<String> {

        var result = memberRepository.deleteMemberById(command.id)

        return ResponseMessage.of(HttpStatus.ACCEPTED, "Member deleted with id: ${command.id} - $result")
    }
}