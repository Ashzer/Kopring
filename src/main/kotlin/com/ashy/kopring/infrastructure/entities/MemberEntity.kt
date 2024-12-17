package com.ashy.kopring.infrastructure.entities

import org.jetbrains.exposed.dao.id.IntIdTable

object MemberEntity : IntIdTable("member") {
    val name = varchar("name", length = 50)
    val age = integer("age")
}