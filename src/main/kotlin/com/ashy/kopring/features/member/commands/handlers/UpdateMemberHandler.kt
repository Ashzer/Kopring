package com.ashy.kopring.features.member.commands.handlers

import an.awesome.pipelinr.Command.Handler
import com.ashy.kopring.features.member.commands.UpdateMember
import com.ashy.kopring.infrastructure.repositories.MemberRepository
import com.ashy.kopring.infrastructure.response.ResponseMessage
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UpdateMemberHandler(private val memberRepository: MemberRepository) :
    Handler<UpdateMember, ResponseMessage<String>> {
    @Transactional
    override fun handle(command: UpdateMember): ResponseMessage<String> {

        val result = memberRepository.updateNameAndAge(command.id, command.name, command.age)

        return ResponseMessage.of(HttpStatus.ACCEPTED, "Member updated with id: ${command.id} - $result")
    }
}