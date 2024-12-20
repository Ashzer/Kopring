package com.ashy.kopring.infrastructure.repositories.impl

import com.ashy.kopring.features.member.commands.CreateMember
import com.ashy.kopring.infrastructure.entities.Member.MemberId
import com.ashy.kopring.infrastructure.entities.MemberEntity
import com.ashy.kopring.infrastructure.extensions.handleDatabaseOperation
import com.ashy.kopring.infrastructure.model.MemberDto
import com.ashy.kopring.infrastructure.repositories.MemberRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryImpl : MemberRepository {
    /*
    override fun create(createMember: CreateMember): MemberId {
        transaction {
            MemberEntity.insertAndGetId {
                it[name] = createMember.name
                it[age] = createMember.age
            }
        }.let { return MemberId(it.value) }
    }*/

    override fun create(createMember: CreateMember) = handleDatabaseOperation {
        MemberEntity.insertAndGetId {
            it[name] = createMember.name
            it[age] = createMember.age
        }.let {
            MemberId(it.value)
        }
    }

    override fun updateNameAndAge(id: Int, name: String, age: Int) = handleDatabaseOperation {
        MemberEntity.update({ MemberEntity.id eq id }) {
            it[MemberEntity.name] = name
            it[MemberEntity.age] = age
        }
    }

    override fun deleteMemberById(id: Int) = handleDatabaseOperation {
        MemberEntity.deleteWhere { MemberEntity.id eq id }
    }

    override fun findAll(): List<MemberDto> {
        return transaction {
            addLogger(StdOutSqlLogger)
            MemberEntity.selectAll().map {
                MemberDto(it[MemberEntity.id].value, it[MemberEntity.name], it[MemberEntity.age])
            }.toList()
        }
    }
}
