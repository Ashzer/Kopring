package com.ashy.kopring.infrastructure.entities

import org.jetbrains.exposed.dao.id.CompositeIdTable

object LossCutEntity : CompositeIdTable("loss_cut") {
    val articleId = reference("article_id", ArticleEntity)
    val memberId = reference("member_id", MemberEntity)
    val lossCut = integer("loss_cut")

    override val primaryKey = PrimaryKey(articleId, memberId)
}