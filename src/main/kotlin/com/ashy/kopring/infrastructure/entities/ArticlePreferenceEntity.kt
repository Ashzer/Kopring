package com.ashy.kopring.infrastructure.entities

import com.ashy.kopring.infrastructure.enums.ArticlePreferenceType
import org.jetbrains.exposed.dao.id.CompositeIdTable

object ArticlePreferenceEntity : CompositeIdTable("article_preference") {
    val articleId = reference("article_id", ArticleEntity.id)
    val memberId = reference("member_id", MemberEntity.id)
    val preference = enumerationByName("preference", 10, ArticlePreferenceType::class)

    override val primaryKey = PrimaryKey(articleId, memberId)
}