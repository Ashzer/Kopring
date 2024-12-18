package com.ashy.kopring.infrastructure.entities

data class Member(
    val id: Member?, val name: String, val age: Int
) {
    @JvmInline
    value class MemberId(val value: Int)
}