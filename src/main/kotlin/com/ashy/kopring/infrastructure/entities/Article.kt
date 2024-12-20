package com.ashy.kopring.infrastructure.entities

data class Article(
    val id: Article?,
    val title: String,
    val content: String,
    val boardId: Board.BoardId,
    val memberId: Member.MemberId,
) {
    @JvmInline
    value class ArticleId(val value: Int)
}
