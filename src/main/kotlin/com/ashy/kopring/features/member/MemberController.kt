package com.ashy.kopring.features.member

import an.awesome.pipelinr.Pipeline
import com.ashy.kopring.features.member.commands.CreateMember
import com.ashy.kopring.features.member.commands.DeleteMember
import com.ashy.kopring.features.member.commands.UpdateMember
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
    suspend fun createMember(@RequestBody createMember: CreateMember) = handleWithResponseMessage(createMember)

    @GetMapping
    suspend fun getMembers() = handleWithResponseMessage(GetMembers())

    @GetMapping("/{memberId}")
    fun getMemberDetail(@PathVariable("memberId") memberId: Int): ResponseEntity<*> =
        ResponseEntity.ok().body("Member$memberId detail")

    @PutMapping("/{memberId}")
    suspend fun updateMember(@PathVariable("memberId") memberId: Int, @RequestBody updateMember: UpdateMember) =
        handleWithResponseMessage(updateMember.copy(id = memberId))

    @DeleteMapping("/{memberId}")
    suspend fun deleteMember(@PathVariable("memberId") memberId: Int) = handleWithResponseMessage(DeleteMember(id = memberId))
}