package com.ashy.kopring.infrastructure.entities

data class Comment(
    val id: Comment?,
    val content: String,
    val articleId: Article.ArticleId,
    val memberId: Member.MemberId
) {
    @JvmInline
    value class CommentId(val value: Int)
}
