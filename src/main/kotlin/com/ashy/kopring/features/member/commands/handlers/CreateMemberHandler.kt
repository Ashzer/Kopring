package com.ashy.kopring.features.member.commands.handlers

import an.awesome.pipelinr.Command.Handler
import com.ashy.kopring.features.member.commands.CreateMember
import com.ashy.kopring.infrastructure.repositories.MemberRepository
import org.springframework.stereotype.Component

@Component
class CreateMemberHandler(private val memberRepository: MemberRepository) : Handler<CreateMember, String> {
    constructor() : this(MemberRepository())

    override fun handle(command: CreateMember): String {

        val id = memberRepository.create(command)

        return "Member created #$id ${command.name} is ${command.age} years old"
    }
}