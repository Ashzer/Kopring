package com.ashy.kopring.infrastructure.entities

import org.jetbrains.exposed.dao.id.LongIdTable

object CommentEntity : LongIdTable("comment") {
    val content = text("content")
    val articleId = reference("article_id", ArticleEntity.id)
    val memberId = reference("member_id", MemberEntity.id)
}