package com.ashy.kopring.infrastructure.repositories

import com.ashy.kopring.features.member.commands.CreateMember
import com.ashy.kopring.features.member.commands.handlers.DeleteMemberHandler
import com.ashy.kopring.infrastructure.entities.Member.MemberId
import com.ashy.kopring.infrastructure.entities.MemberEntity
import com.ashy.kopring.infrastructure.model.MemberDto
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class MemberRepository {
    private final val deleteMemberHandler: DeleteMemberHandler = TODO("initialize me")

    fun create(createMember: CreateMember): MemberId {
        transaction {
            MemberEntity.insertAndGetId {
                it[name] = createMember.name
                it[age] = createMember.age
            }
        }.let { return MemberId(it.value) }


    }

    fun updateNameAndAge(id: Int, name: String, age: Int): Int {
        MemberEntity.update({ MemberEntity.id eq id }) {
            it[MemberEntity.name] = name
            it[MemberEntity.age] = age
        }.let { return it }
    }

    fun deleteMemberById(id: Int): Int {
        MemberEntity.deleteWhere { MemberEntity.id eq id }.let { return it }
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
            MemberEntity.selectAll().map {
                MemberDto(it[MemberEntity.id].value, it[MemberEntity.name], it[MemberEntity.age])
            }
        }
    }
}