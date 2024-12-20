package com.ashy.kopring.infrastructure.entities

import com.ashy.kopring.infrastructure.enums.ArticlePreferenceType

data class ArticlePreference(
    val articleId: Article.ArticleId, val memberId: Member.MemberId, val preference: ArticlePreferenceType
)