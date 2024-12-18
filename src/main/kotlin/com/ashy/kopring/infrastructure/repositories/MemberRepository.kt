package com.ashy.kopring.infrastructure.repositories

import com.ashy.kopring.features.member.commands.CreateMember
import com.ashy.kopring.infrastructure.entities.Member
import com.ashy.kopring.infrastructure.entities.Member.MemberId
import com.ashy.kopring.infrastructure.entities.MemberEntity
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class MemberRepository {
    fun create(createMember: CreateMember): MemberId {
        val id = MemberEntity.insertAndGetId {
            it[name] = createMember.name
            it[age] = createMember.age
        }
        return MemberId(id.value)
    }

    fun findById(id: MemberId): MemberEntity? {
        transaction {
            return MemberEntity.selectAll().where { MemberEntity.id eq id.value}.fi

        }
    }
}