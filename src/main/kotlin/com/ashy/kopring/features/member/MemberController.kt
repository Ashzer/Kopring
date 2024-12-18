package com.ashy.kopring.features.member

import an.awesome.pipelinr.Pipeline
import com.ashy.kopring.features.member.commands.CreateMember
import com.ashy.kopring.features.member.queries.GetMembers
import com.ashy.kopring.infrastructure.platform.BaseUserController
import com.ashy.kopring.infrastructure.repositories.MemberRepository
import com.ashy.kopring.infrastructure.services.AuthenticationManager
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/member")
class MemberController(
    pipeline: Pipeline, authenticationManager: AuthenticationManager, memberRepository: MemberRepository
) : BaseUserController(
    pipeline, authenticationManager, memberRepository
) {

    @PostMapping
    fun createMember(@RequestBody createMember: CreateMember): ResponseEntity<*> {
        return handleWithResponseMessage(createMember)
    }

    @GetMapping
    fun getMembers(): ResponseEntity<*> {
        val getMembers = GetMembers()
        return handleWithResponseMessage(getMembers)
    }

    @GetMapping("/{memberId}")
    fun getMemberDetail(@PathVariable("memberId") memberId: Int): ResponseEntity<*> =
        ResponseEntity.ok().body("Member$memberId detail")

    @PutMapping("/{memberId}")
    fun updateMember(@PathVariable("memberId") memberId: Int): ResponseEntity<*> =
        ResponseEntity.accepted().body("Member$memberId updated")

}

