package com.ashy.kopring.infrastructure.entities

data class LossCut(
    val loss: Int, val articleId: Article.ArticleId, val memberId: Member.MemberId
)
