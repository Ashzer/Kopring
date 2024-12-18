package com.ashy.kopring.infrastructure.repositories

import com.ashy.kopring.features.member.commands.CreateMember
import com.ashy.kopring.infrastructure.entities.Member
import com.ashy.kopring.infrastructure.entities.Member.MemberId
import com.ashy.kopring.infrastructure.entities.MemberEntity
import com.ashy.kopring.infrastructure.model.MemberDto
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
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

    fun findById(id: Int): MemberDto? {
        transaction {
            addLogger(StdOutSqlLogger)
            MemberEntity.selectAll().where { MemberEntity.id eq id }.first()
        }.let {
            return MemberDto(it[MemberEntity.id].value, it[MemberEntity.name], it[MemberEntity.age])
        }
    }

    fun findAll(): List<MemberDto> {
        return transaction {
            addLogger(StdOutSqlLogger)
            MemberEntity.selectAll().map {
                MemberDto(it[MemberEntity.id].value, it[MemberEntity.name], it[MemberEntity.age])
            }
        }
    }
}