package com.ashy.kopring.features.member

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/member")
class MemberController {

    @PostMapping
    fun createMember(): ResponseEntity<*> = ResponseEntity.accepted().body(
        "Member created"
    )

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
