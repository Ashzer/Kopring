package com.ashy.kopring.infrastructure.repositories

import com.ashy.kopring.features.member.commands.CreateMember
import com.ashy.kopring.infrastructure.entities.Member.MemberId
import com.ashy.kopring.infrastructure.model.MemberDto

interface MemberRepository {
    fun deleteMemberById(id: Int): Result<Int>
    fun findAll(): List<MemberDto>
    fun updateNameAndAge(id: Int, name: String, age: Int): Result<Int>
    fun create(createMember: CreateMember): Result<MemberId>
}