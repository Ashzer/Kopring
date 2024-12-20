package com.ashy.kopring.infrastructure.entities

import org.jetbrains.exposed.dao.id.IntIdTable

object ArticleEntity : IntIdTable("article") {
    val title  = varchar("title", length = 50)
    val content = text("content")
    val boardId = reference("board_id", BoardEntity.id)
    val memberId = reference("member_id", MemberEntity.id)
}