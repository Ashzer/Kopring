package com.ashy.kopring.infrastructure.entities

import org.jetbrains.exposed.dao.id.IntIdTable

object BoardEntity : IntIdTable("board") {
    val title = varchar("title", length = 50)
    val memberId = reference("member_id", MemberEntity.id)
}