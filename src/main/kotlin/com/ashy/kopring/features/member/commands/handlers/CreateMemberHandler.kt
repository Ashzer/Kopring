package com.ashy.kopring.features.member.commands.handlers

import an.awesome.pipelinr.Command.Handler
import com.ashy.kopring.features.member.commands.CreateMember
import com.ashy.kopring.infrastructure.repositories.MemberRepository
import com.ashy.kopring.infrastructure.response.ResponseMessage
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class CreateMemberHandler(private val memberRepository: MemberRepository) :
    Handler<CreateMember, ResponseMessage<String>> {

    override fun handle(command: CreateMember): ResponseMessage<String> {

        val id = memberRepository.create(command)

        return ResponseMessage.of(HttpStatus.ACCEPTED, "Member created with id: $id")
    }
}