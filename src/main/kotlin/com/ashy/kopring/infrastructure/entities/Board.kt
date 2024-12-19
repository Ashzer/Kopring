package com.ashy.kopring.infrastructure.entities

data class Board(val id: Board?, val title: String, val memberId: Member.MemberId) {
    @JvmInline
    value class BoardId(val value: Int)
}
