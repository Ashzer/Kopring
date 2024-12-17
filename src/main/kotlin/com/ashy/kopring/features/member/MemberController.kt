package com.ashy.kopring.features.member

import an.awesome.pipelinr.CommandHandlers
import an.awesome.pipelinr.Pipeline
import an.awesome.pipelinr.Pipelinr
import com.ashy.kopring.features.member.commands.CreateMember
import com.ashy.kopring.features.member.commands.handlers.CreateMemberHandler
import com.ashy.kopring.infrastructure.repositories.MemberRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Stream

@RestController
@RequestMapping("/member")
class MemberController(private val memberRepository: MemberRepository) {

    @PostMapping
    fun createMember(@RequestBody createMember: CreateMember): ResponseEntity<*> {
        val handler = CommandHandlers{ Stream.of(CreateMemberHandler(memberRepository))}
        val result = createMember.execute(Pipelinr().with(handler))


        return ResponseEntity.accepted().body(
            result
        )
    }

    @GetMapping
    fun getMembers(): ResponseEntity<*> = ResponseEntity.ok()
        .body(listOf("Hello, Member1!", "Hello, Member2!", "Hello, Member3!", "Hello, Member4!", "Hello, Member5!"))

    @GetMapping("/{memberId}")
    fun getMemberDetail(@PathVariable("memberId") memberId: Int): ResponseEntity<*> =
        ResponseEntity.ok().body("Member$memberId detail")

    @PutMapping("/{memberId}")
    fun updateMember(@PathVariable("memberId") memberId: Int): ResponseEntity<*> =
        ResponseEntity.accepted().body("Member$memberId updated")
}
