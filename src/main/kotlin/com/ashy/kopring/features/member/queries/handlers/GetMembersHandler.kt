package com.ashy.kopring.features.member.queries.handlers

import an.awesome.pipelinr.Command
import com.ashy.kopring.features.member.queries.GetMembers
import com.ashy.kopring.infrastructure.model.MemberDto
import com.ashy.kopring.infrastructure.repositories.MemberRepository
import com.ashy.kopring.infrastructure.response.ResponseMessage
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class GetMembersHandler(private val memberRepository: MemberRepository) :
    Command.Handler<GetMembers, ResponseMessage<List<MemberDto>>> {
    override fun handle(p0: GetMembers?): ResponseMessage<List<MemberDto>> {
        return ResponseMessage.of(HttpStatus.OK, memberRepository.findAll())
    }
}